package com.garbage.demo.vo.request;

import com.garbage.demo.common.Search;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class RequestUserListVo extends Search {

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String tel;

    private String address;
    /**
     * 用户权限(0为员工，1为管理员，2为高级管理员）
     */
    private Integer type;


}
