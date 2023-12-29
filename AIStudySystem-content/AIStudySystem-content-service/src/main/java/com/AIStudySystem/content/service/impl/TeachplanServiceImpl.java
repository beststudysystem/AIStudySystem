package com.AIStudySystem.content.service.impl;

import com.AIStudySystem.content.mapper.TeachplanMapper;
import com.AIStudySystem.content.model.dto.SaveTeachplanDto;
import com.AIStudySystem.content.model.dto.TeachplanDto;
import com.AIStudySystem.content.model.po.Teachplan;
import com.AIStudySystem.content.service.TeachplanService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeachplanServiceImpl implements TeachplanService {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Override
    public List<TeachplanDto> findTeachplanTree(long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    @Transactional
    @Override
    public void saveTeachplan(SaveTeachplanDto teachplanDto) {
        //课程计划id
        Long id = teachplanDto.getId();
        //修改课程计划
        if(id!=null){
            Teachplan teachplan = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(teachplanDto,teachplan);
            teachplanMapper.updateById(teachplan);
        }
        //新增课程计划
        else{
            //取出同父同级别的课程计划数量
            int count = getTeachplanCount(teachplanDto.getCourseId(), teachplanDto.getParentid());
            Teachplan teachplanNew = new Teachplan();
            //设置排序号
            teachplanNew.setOrderby(count+1);
            BeanUtils.copyProperties(teachplanDto,teachplanNew);

            teachplanMapper.insert(teachplanNew);
        }
    }


    /**
     * @description 获取最新的排序号
     * @param courseId  课程id
     * @param parentId  父课程计划id
     * @return int 最新排序号
     * @author Jasper
     * @date 2023/12/30 01:44
     */
    private int getTeachplanCount(long courseId,long parentId){
        // 确定排序字段，找到它的同级节点个数，排序字段就是同级节点个数+1
        // sql：select count(*) from teachplan where courseid = ？ and parentid = ？
        // - parentid = 0 说明要添加的是某个courseid下的一级节点（大标题），
        //      - 比如：courseid = 117 and parentid = 0，说明要添加的是117课程下的一级节点（大标题）
        // - parentid != 0 说明要添加的是某个courseid下的二级节点（小标题）,
        //      - 比如：courseid = 117 and parentid = 268，说明要添加的是117课程下的268节点（大标题）下的的子节点（小标题）
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId,courseId);
        queryWrapper.eq(Teachplan::getParentid,parentId);
        Integer count = teachplanMapper.selectCount(queryWrapper);
        return count;
    }

}
