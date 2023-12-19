package com.AIStudySystem.content.mapper;

import com.AIStudySystem.content.model.dto.CourseCategoryTreeDto;
import com.AIStudySystem.content.model.po.CourseCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {
    /**
     * 查询所有子节点
     * @return List<CourseCategoryTreeDto>
     */
    public List<CourseCategoryTreeDto> selectTreeNodes(String id);


}
