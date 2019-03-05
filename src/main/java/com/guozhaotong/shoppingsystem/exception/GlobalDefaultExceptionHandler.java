package com.guozhaotong.shoppingsystem.exception;

import com.alibaba.fastjson.JSON;
import com.guozhaotong.shoppingsystem.entity.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @author 郭朝彤
 * @date 2017/10/31.
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public void defaultErrorHandler(HttpServletRequest req, Exception e, HttpServletResponse response) throws IOException {
        logger.error(e.getMessage(),e);
        Writer writer = response.getWriter();
        ResultEntity resultEntity = new ResultEntity(500, "Internal error", null);
        writer.write(JSON.toJSONString(resultEntity));
    }

}
