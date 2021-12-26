package com.garbage.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.garbage.demo.entity.Notice;
import com.garbage.demo.mapper.NoticeMapper;
import com.garbage.demo.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.garbage.demo.vo.request.RequestNoticeListVo;
import com.garbage.demo.vo.request.RequestSaveOrUpdateNoticeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公告表 服务实现类
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Autowired
    INoticeService noticeService;

    @Override
    public IPage<Notice> noticeList(RequestNoticeListVo noticeListVo) {
        IPage<Notice> noticeIPage = new Page<>(noticeListVo.getDisplayStart(),noticeListVo.getDisplayLength());
        QueryWrapper<Notice> noticeQueryWrapper = new QueryWrapper<>();
        if(ObjectUtils.isNotEmpty(noticeListVo.getTitle())){
            noticeQueryWrapper.like("title",noticeListVo.getTitle());
        }
        if(ObjectUtils.isNotEmpty(noticeListVo.getNoticeText())){
            noticeQueryWrapper.like("notice_text",noticeListVo.getNoticeText());
        }
        if(ObjectUtils.isNotEmpty(noticeListVo.getCreateTime())){
            noticeQueryWrapper.like("create_time",noticeListVo.getCreateTime());
        }
        return page(noticeIPage,noticeQueryWrapper);
    }
}
