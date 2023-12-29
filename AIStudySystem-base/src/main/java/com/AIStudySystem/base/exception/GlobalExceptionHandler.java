package com.AIStudySystem.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @description 对系统的自定义异常做统一处理
     * @param e
     * @return com.AIStudySystem.base.exception.RestErrorResponse
     */
    @ExceptionHandler(AIStudySystemException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse customException(AIStudySystemException e){
        // 捕获到的各种异常的处理都可以在这里进行：比如重试、记录日志、发送邮件、短信通知等

        // 记录异常信息
        log.error("AIStudySystem系统异常：{}",e.getErrMessage());
        // 解析出异常信息
        return new RestErrorResponse(e.getErrMessage());
    }

    /**
     * @description 对系统的其他异常做统一处理
     * @param e
     * @return com.AIStudySystem.base.exception.RestErrorResponse
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse exception(Exception e){
        // 记录异常信息
        log.error("系统异常：{}",e.getMessage());
        // 统一提示CommonError.UNKOWN_ERROR.getErrMessage() <- "执行过程异常，请重试。"
        return new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> msgList = new ArrayList<>();
        //将错误信息放在msgList
        bindingResult.getFieldErrors().stream().forEach(item->msgList.add(item.getDefaultMessage()));
        //拼接错误信息
        String msg = StringUtils.join(msgList, ",");
        log.error("【系统异常】{}",msg);
        return new RestErrorResponse(msg);
    }



}
