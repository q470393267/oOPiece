package com.garbage.demo.vo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestGarbageAddVo {

    private Integer id;

    private Integer type;

    /**
     * 垃圾重量（单位：kg)
     */
    private Double weight;

    private String origin;

}
