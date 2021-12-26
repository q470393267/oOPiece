package com.garbage.demo.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseTransportListVo{
    private Integer id;
    private Double weight;
    /**
     * 目的地
     */
    private String dest;

    private Integer type;

    /**
     * 运出时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private LocalDate outTime;

    /**
     * 审核状态(0为申请中，1为申请成功，2为申请失败）
     */
    private Integer status;

}
