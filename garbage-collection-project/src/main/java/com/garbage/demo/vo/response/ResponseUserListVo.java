package com.garbage.demo.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class ResponseUserListVo {
    private String id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（0为女，1为男）
     */
    private Integer sex;

    /**
     * 生日
     * 地址
     */
    private LocalDate birthday;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String tel;

    /**
     */
    private String address;

    /**
     * 用户权限(0为员工，1为管理员，2为高级管理员）
     */
    private Integer type;
    /**
     * 登陆者权限(0为员工，1为管理员，2为高级管理员）
     */
    private Integer userType;

    /**
     * 用户头像地址
     */
    private String avatarUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;
}

