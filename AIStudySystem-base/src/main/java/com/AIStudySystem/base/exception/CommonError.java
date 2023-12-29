package com.AIStudySystem.base.exception;

import lombok.Getter;

/**
 * @description 一个枚举类，系统中的通用错误信息
 * @author Jasper
 * @date 2023/12/22 16:02
 * @version 1.1
 */
@Getter
public enum CommonError {

    UNKOWN_ERROR("执行过程异常，请重试。"),
    PARAMS_ERROR("非法参数"),
    OBJECT_NULL("对象为空"),
    QUERY_NULL("查询结果为空"),
    REQUEST_NULL("请求参数为空");

    private String errMessage;

    private CommonError( String errMessage) {
        this.errMessage = errMessage;
    }

}
