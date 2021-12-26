package com.garbage.demo.vo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RequestLoginVo {
    private String usernameOrTel;
    private String password;
    private String captcha;
}
