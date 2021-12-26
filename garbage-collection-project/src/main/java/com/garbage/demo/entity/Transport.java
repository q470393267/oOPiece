package com.garbage.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 运输表
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Transport implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;


}
