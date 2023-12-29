package com.AIStudySystem.content.service.impl;

import com.AIStudySystem.base.exception.AIStudySystemException;
import com.AIStudySystem.base.model.PageParams;
import com.AIStudySystem.base.model.PageResult;
import com.AIStudySystem.content.mapper.CourseBaseMapper;
import com.AIStudySystem.content.mapper.CourseCategoryMapper;
import com.AIStudySystem.content.mapper.CourseMarketMapper;
import com.AIStudySystem.content.model.dto.AddCourseDto;
import com.AIStudySystem.content.model.dto.CourseBaseInfoDto;
import com.AIStudySystem.content.model.dto.EditCourseDto;
import com.AIStudySystem.content.model.dto.QueryCourseParamsDto;
import com.AIStudySystem.content.model.po.CourseBase;
import com.AIStudySystem.content.model.po.CourseMarket;
import com.AIStudySystem.content.service.CourseBaseInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {

    @Autowired
    CourseBaseMapper courseBaseMapper;
    @Autowired
    CourseMarketMapper courseMarketMapper;
    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParams) {
        // 流程：1.拼装查询条件pageParams和queryCourseParams 2.查询 3.封装成PageResult再return
        // 1.拼接查询条件
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //根据课程名称模糊查询  name like '%名称%'
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParams.getCourseName()),
                CourseBase::getName,
                queryCourseParams.getCourseName());
        //根据课程审核状态
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParams.getAuditStatus()),
                CourseBase::getAuditStatus,
                queryCourseParams.getAuditStatus());
        //todo: 根据课程发布状态查询
        //分页参数
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());

        //分页查询E page 分页参数, @Param("ew") Wrapper<T> queryWrapper 查询条件
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);

        //数据
        List<CourseBase> items = pageResult.getRecords();
        //总记录数
        long total = pageResult.getTotal();

        //准备返回数据 List<T> items, long counts, long page, long pageSize
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(items, total, pageParams.getPageNo(), pageParams.getPageSize());
        System.out.println(courseBasePageResult);
        return courseBasePageResult;
    }

    @Transactional
    @Override
    public CourseBaseInfoDto createCourseBase(Long companyID, AddCourseDto dto) {
        // TODO: 机构id在做完认证系统后，需要从认证系统获取机构id(由于认证系统没有，暂时硬编码)
        // 将来会集成spring security框架，实现单点登录，用户登录后，这里就能获取到用户所属机构的id（会把id传到service），所以要在参数里定义一个companyId

        /*
         * 1. 业务逻辑校验
         */

        /*
         * 2. 创建po对象：course_base，并写入数据
         */
        CourseBase courseBaseNew = new CourseBase();

        // // 将dto的数据复制到courseBaseNew,方式1:手动复制
        // courseBaseNew.setName(dto.getName());
        // courseBaseNew.setUsers(dto.getUsers());

        // 将dto的数据复制到courseBaseNew,方式2:使用spring的BeanUtils.copyProperties方法
        BeanUtils.copyProperties(dto, courseBaseNew);
        courseBaseNew.setCompanyId(companyID);
        courseBaseNew.setCreateDate(LocalDateTime.now());

        // 设置默认状态（需要去查dictionary）：
        // 审核状态默认为未提交
        courseBaseNew.setAuditStatus("202002");
        // 设置课程状态默认为未发布
        courseBaseNew.setStatus("203001");

        /*
         * 3. 将course_base插入数据库，用注入的mapper对象
         */
        int insert = courseBaseMapper.insert(courseBaseNew);
        if(insert <= 0){
            // throw new RuntimeException("添加课程基本信息失败");
            AIStudySystemException.cast("添加课程基本信息失败");
        }

        /*
         * （同1，2，3）向课程营销信息表course_market写入数据
         * - 需要先创建po对象：course_market，并写入数据
         * - 将页面输入的数据复制到course_market
         * - 拿到course_base的id，设置到course_market的id (因为一对一：course_base表的id作为course_market表的id)
         * -
         */
        CourseMarket courseMarketNew = new CourseMarket();
        BeanUtils.copyProperties(dto, courseMarketNew);
        Long id = courseBaseNew.getId();
        courseMarketNew.setId(id);
        // saveCourseMarket方法：保存营销信息；逻辑：存在就更新，不存在就添加
        saveCourseMarket(courseMarketNew);

        // getCourseBaseInfo方法：从数据库查询课程的详细信息，包括两部分：course_base和course_market
        return getCourseBaseInfo(id);
    }

    @Override
    public CourseBaseInfoDto getCourseBaseInfo(Long courseId){
        // 1. 查询course_base
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null) {
            return null;
        }
        // 2. 查询course_market
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        // 3. 将course_base和course_market的数据复制到CourseBaseInfoDto
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase, courseBaseInfoDto);
        if (courseMarket != null) {
            BeanUtils.copyProperties(courseMarket, courseBaseInfoDto);
        }
        // TODO: 4. 课程分类的名称设置到CourseBaseInfoDto
        courseBaseInfoDto.setMtName(courseCategoryMapper.selectById(courseBase.getMt()).getName());
        courseBaseInfoDto.setStName(courseCategoryMapper.selectById(courseBase.getSt()).getName());

        // 5. 返回
        return courseBaseInfoDto;
    }

    // 单独写一个方法保存营销信息，逻辑：存在就更新，不存在就添加
    public int saveCourseMarket(CourseMarket courseMarketNew){
        // 合法性校验（业务逻辑校验）
        String charge = courseMarketNew.getCharge();
        if (StringUtils.isBlank(charge)) {
            // throw new RuntimeException("收费规则为空");
            AIStudySystemException.cast("收费规则为空");
        }
        // 如果选了“收费”，就需要检查价格，如果没写价格，就抛异常
        if(charge.equals("201001")){
            Float price = courseMarketNew.getPrice();
            if(price == null || price <= 0){
                // throw new RuntimeException("价格不能为空，且必须大于0");
                AIStudySystemException.cast("价格不能为空，且必须大于0");
            }
        }
        // 1. 先查询是否存在
        CourseMarket courseMarketOld = courseMarketMapper.selectById(courseMarketNew.getId());
        // 2. 如果不存在，就添加
        if(courseMarketOld == null){
            int i = courseMarketMapper.insert(courseMarketNew);
            return i;
        }
        // 3. 如果存在，就更新
        else{
            // 将courseMarketNew的数据复制到courseMarketOld
            BeanUtils.copyProperties(courseMarketNew, courseMarketOld);
            courseMarketOld.setId(courseMarketNew.getId());
            // 更新
            int i = courseMarketMapper.updateById(courseMarketNew);
            return i;
        }
    }

    @Override
    public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto dto) {
        // TODO: 机构id在做完认证系统后，需要从认证系统获取机构id(由于认证系统没有，暂时硬编码)
        // 将来会集成spring security框架，实现单点登录，用户登录后，这里就能获取到用户所属机构的id（会把id传到service），所以要在参数里定义一个companyId

        /*
         * 1. 业务逻辑校验
         */
        // 本机构只能修改本机构的课程
        Long id = dto.getId();
        CourseBase courseBase = courseBaseMapper.selectById(id);
        if(courseBase == null){
            AIStudySystemException.cast("课程不存在");
        }
        if(!companyId.equals(courseBase.getCompanyId())){
            AIStudySystemException.cast("只能修改本机构的课程");
        }

        /*
         * 2. 完成业务逻辑
         */
        BeanUtils.copyProperties(dto, courseBase);
        courseBase.setChangeDate(LocalDateTime.now());
        int i = courseBaseMapper.updateById(courseBase);
        if (i <= 0){
            AIStudySystemException.cast("修改课程基本信息失败");
        }

        // TODO：更新课程营销信息

        return getCourseBaseInfo(id);
    }


}
