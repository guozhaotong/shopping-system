package com.guozhaotong.shoppingsystem.service;

import com.guozhaotong.shoppingsystem.entity.UserInfo;
import com.guozhaotong.shoppingsystem.mapper.UserInfoMapper;
import com.guozhaotong.shoppingsystem.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 郭朝彤
 * @date 2019/3/3.
 */
@Service
public class UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 数据库中存放的是用base64加密后的密码，故先对数据库中的密码进行解密，然后用MD5加密，验证前端传来的密码是否正确。
     * @param userName
     * @param passwordMd
     * @return
     */
    public boolean login(String userName, String passwordMd){
        String passwordBase64 = userInfoMapper.findPasswordByName(userName);
        String password = Encryption.deBase64(passwordBase64);
        return Encryption.md5(password).equals(passwordMd);
    }

    public UserInfo findByUserName(String userName){
        return userInfoMapper.findByName(userName);
    }
}
