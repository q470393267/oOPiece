package com.garbage.demo.vo.request;

import com.garbage.demo.common.Search;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter@Setter
public class RequestTransportListVo extends Search {
    /**
     * 目的地
     */
    private String dest;

    private Integer type;
    /**
     * 运出时间
     */
    private LocalDate outTime;

    /**
     * 审核状态(0为申请中，1为申请成功，2为申请失败）
     */
    private Integer status;

}
