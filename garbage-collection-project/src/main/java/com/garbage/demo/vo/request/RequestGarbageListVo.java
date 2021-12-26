package com.garbage.demo.vo.request;

import com.garbage.demo.common.Search;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class RequestGarbageListVo extends Search {
    /**
     * 垃圾类型
     */
    private Integer type;

    /**
     * 状态（0为在厂，1为出厂）
     */
    private Integer status;

    private LocalDate entryTime;
}
