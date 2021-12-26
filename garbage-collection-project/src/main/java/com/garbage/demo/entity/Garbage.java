package com.garbage.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.garbage.demo.common.Search;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 垃圾入库表
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Garbage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 垃圾类型
     */
    private Integer type;

    /**
     * 垃圾重量（单位：kg)
     */
    private Double weight;

    /**
     * 状态（0为在厂，1为出厂）
     */
    private Integer status;

    private String origin;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createTime;

    private Integer transportId;

}
