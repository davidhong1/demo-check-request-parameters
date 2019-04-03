package com.yuan.democheckrequestparameters.exception;

import com.yuan.democheckrequestparameters.domain.BRStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

/**
 * @author: mac
 * @date: 2019-01-21
 * @description:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(CustomException.class)
    public void handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
        log.info("CustomException");
        res.sendError(ex.getHttpStatus().value(), ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException(HttpServletResponse resp, ConstraintViolationException ex) throws IOException {
        log.info("ConstraintViolationException");
        String msg = ex.getMessage();
        String[] msgs = msg.split(": ");
        resp.sendError(HttpStatus.OK.value(), BRStatus.PARAS_ERROR.getStatus() +  msgs[msgs.length-1]);
    }

    /*
    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse res) throws IOException {
        log.info("Exception");
        res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "SERVER ERROR");
    }
    */

}
