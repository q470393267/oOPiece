package com.garbage.demo.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
        log.error("异常信息：{}", e.getMessage());
        return Result.getFailure().setData(e.getMessage());
    }
}
