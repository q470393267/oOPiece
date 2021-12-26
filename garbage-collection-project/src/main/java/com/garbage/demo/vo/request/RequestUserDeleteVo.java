package com.garbage.demo.vo.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * @Author:JeremyLi
 * @Description:
 * @Date: 2020/12/20
 */
@Getter
@Setter
public class RequestUserDeleteVo {
    private String[] ids;

    public List<String> getStringIds(){
        return  Arrays.asList(ids);
    }
}
