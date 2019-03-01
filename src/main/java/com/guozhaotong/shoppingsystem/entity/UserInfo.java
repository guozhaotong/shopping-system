package com.guozhaotong.shoppingsystem.entity;

/**
 * @author 郭朝彤
 * @date 2019/3/1.
 */

public class UserInfo {
    long id;
    String name;
    //0 is seller; 1 is buyer
    String identity;
    String password;

    public UserInfo(String name, String identity, String password) {
        this.name = name;
        this.identity = identity;
        this.password = password;
    }

    public UserInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identity='" + identity + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
