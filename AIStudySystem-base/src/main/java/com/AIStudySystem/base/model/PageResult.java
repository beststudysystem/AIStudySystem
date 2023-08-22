package com.AIStudySystem.base.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

// 用泛型，因为可能传不同类型的 object 进去，比如课程就是 course-base
// 主结构：数据列表 + 分页信息
// 这个结构对所有分页查询都有效所以这个类建在 AIStudySystem 里的 base.model
/**
 * @author Jasper
 * @version 1.0
 * @description 分页查询结果模型类（返回的结果）
 * @date 2023/8/22 1:18
 */
@Data
@ToString
public class PageResult<T> implements Serializable {

    // 数据列表
    private List<T> items;

    //总记录数
    private long counts;

    //当前页码
    private long page;

    //每页记录数
    private long pageSize;

    public PageResult(List<T> items, long counts, long page, long pageSize) {
        this.items = items;
        this.counts = counts;
        this.page = page;
        this.pageSize = pageSize;
    }



}

