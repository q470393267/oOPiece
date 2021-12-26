package com.garbage.demo.vo.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Getter
@Setter
public class RequestUpdateUserVo {
    private String id;
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
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

}
