package com.AIStudySystem.base.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description 错误响应参数包装(和前端约定反回的异常信息)
 * @author Jasper
 * @date 2023/12/22 15:50
 * @version 1.1
 */
@Setter
@Getter
public class RestErrorResponse implements Serializable {

    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

}
