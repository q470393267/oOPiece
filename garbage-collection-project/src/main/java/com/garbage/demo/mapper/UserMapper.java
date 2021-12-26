package com.garbage.demo.mapper;

import com.garbage.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> queryList(String id);
}
