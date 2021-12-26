package com.garbage.demo.mapper;

import com.garbage.demo.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 公告表 Mapper 接口
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

}
