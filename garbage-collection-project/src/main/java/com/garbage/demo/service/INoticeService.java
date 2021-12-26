package com.garbage.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.garbage.demo.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.garbage.demo.vo.request.RequestNoticeListVo;
import com.garbage.demo.vo.request.RequestSaveOrUpdateNoticeVo;

/**
 * <p>
 * 公告表 服务类
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
public interface INoticeService extends IService<Notice> {
     IPage<Notice> noticeList(RequestNoticeListVo noticeListVo);
}
