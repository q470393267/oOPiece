package com.garbage.demo.mapper;

import com.garbage.demo.entity.Transport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 运输表 Mapper 接口
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
@Mapper
public interface TransportMapper extends BaseMapper<Transport> {
    @Select("UPDATE transport SET status = 2 WHERE status = 0 AND out_time < NOW()")
    void updateStatus();
}
