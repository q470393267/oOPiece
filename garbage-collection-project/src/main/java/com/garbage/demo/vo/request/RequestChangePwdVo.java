package com.garbage.demo.vo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestChangePwdVo {
    private String id;

    /**
     * 旧密码
     */
    private String oldPassword;

    private String newPassword;


}
