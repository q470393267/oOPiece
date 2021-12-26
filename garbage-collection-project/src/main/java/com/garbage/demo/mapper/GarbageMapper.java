package com.garbage.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.garbage.demo.entity.Garbage;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 垃圾入库表 Mapper 接口
 * </p>
 *
 * @author lzf
 * @since 2020-10-26
 */
@Mapper
public interface GarbageMapper extends BaseMapper<Garbage> {

}
