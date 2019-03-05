package com.guozhaotong.shoppingsystem.controller;

import com.guozhaotong.shoppingsystem.entity.ResultEntity;
import com.guozhaotong.shoppingsystem.entity.UserInfo;
import com.guozhaotong.shoppingsystem.service.UserInfoService;
import com.guozhaotong.shoppingsystem.util.Encryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 郭朝彤
 * @date 2019/3/3.
 */
@RestController
public class UserInfoController {

    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);


    public static Map<String, Long> tokenMap = new HashMap<>();

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/login")
    public ResultEntity login(String userName, String passwordMd, HttpServletResponse httpServletResponse) {
        boolean infoIsTrue = userInfoService.login(userName, passwordMd);
        if (infoIsTrue) {
            UserInfo userInfo = userInfoService.findByUserName(userName);
            //token为用base64加密后的用户ID+身份
            String token = Encryption.base64(userInfo.getId() + " " + userInfo.getIdentity());
            tokenMap.put(token, System.currentTimeMillis());
            logger.debug(token);
            httpServletResponse.addCookie(new Cookie("token", token));
            return new ResultEntity(200, "success!", null);
        } else {
            return new ResultEntity(403, "fail!", null);
        }
    }

    public static void main(String[] args) {

    }
}
