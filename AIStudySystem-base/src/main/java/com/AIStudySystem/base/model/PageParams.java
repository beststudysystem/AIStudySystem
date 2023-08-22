package com.AIStudySystem.base.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author Jasper
 * @version 1.0
 * @description 分页查询的分页参数（接收的参数）
 * @date 2023/8/21 11:51
 */
// 所有地方都需要的参数（分页信息）
@Data
@ToString
public class PageParams {
    //当前页码
    //用 long 是因为 mybatis-plus 接口的分页参数类型是 long
    private Long pageNo = 1L;

    //每页记录数默认值（每页显示记录数）
    private Long pageSize =30L;

    public PageParams(){

    }

    public PageParams(long pageNo,long pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

}
