package com.garbage.demo.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Search{
    /**
     * 开始查询页数
     */
    private Long displayStart;
    /**
     * 每页展示条数
     */
    private Long displayLength;
}