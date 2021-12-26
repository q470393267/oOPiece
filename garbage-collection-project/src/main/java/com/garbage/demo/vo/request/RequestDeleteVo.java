package com.garbage.demo.vo.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;


@Getter
@Setter
public class RequestDeleteVo {
    private Integer[] ids;

    public List<Integer> getIntegerIds(){
        return  Arrays.asList(ids);
    }
}
