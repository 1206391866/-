package com.wh.bank.controller;

import com.wh.bank.exception.APIException;
import com.wh.bank.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice  //捕获全局异常
public class ExceptionCountrollerAdivce {
    @ExceptionHandler({Exception.class})//捕获的异常类型APie
    public Result apiExceptionHandler(Exception e) {
        log.error(e.getMessage());
        return Result.error("系统异常");
    }
}
