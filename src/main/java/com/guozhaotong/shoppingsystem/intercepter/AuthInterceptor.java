package com.guozhaotong.shoppingsystem.intercepter;

import com.alibaba.fastjson.JSON;
import com.guozhaotong.shoppingsystem.controller.UserInfoController;
import com.guozhaotong.shoppingsystem.entity.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

/**
 * @author 郭朝彤
 * @date 2019/3/3.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        return true;
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            Writer writer = response.getWriter();
            ResultEntity resultEntity = new ResultEntity(403, "please login", null);
            writer.write(JSON.toJSONString(resultEntity));
            return false;
        }
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        logger.debug(token);
        if (token == null) {
            Writer writer = response.getWriter();
            ResultEntity resultEntity = new ResultEntity(403, "please login", null);
            writer.write(JSON.toJSONString(resultEntity));
            return false;
        } else {
            if (UserInfoController.tokenMap.containsKey(token)) {
                long timeOfToken = UserInfoController.tokenMap.get(token);
                if (System.currentTimeMillis() - timeOfToken > (30 * 60 * 1000)) {
                    Writer writer = response.getWriter();
                    ResultEntity resultEntity = new ResultEntity(403, "please login", null);
                    writer.write(JSON.toJSONString(resultEntity));
                    return false;
                } else {
                    UserInfoController.tokenMap.put(token, System.currentTimeMillis());
                    return true;
                }
            } else {
                Writer writer = response.getWriter();
                ResultEntity resultEntity = new ResultEntity(403, "please login", null);
                writer.write(JSON.toJSONString(resultEntity));
                return false;
            }
        }
    }
}
