package com.AIStudySystem.base.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description 系统中的自定义异常（继承RuntimeException）
 * @author Jasper
 * @date 2023/12/22 15:55
 * @version 1.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AIStudySystemException extends RuntimeException{
    private String errMessage;

    public AIStudySystemException() {
        super();
    }

    public AIStudySystemException(String message) {
        super(message);
        this.errMessage = message;
    }

    public static void cast(CommonError commonError){
        throw new AIStudySystemException(commonError.getErrMessage());
    }

    public static void cast(String errMessage){
        throw new AIStudySystemException(errMessage);
    }


}
