package com.garbage.demo.vo.request;

import com.garbage.demo.common.Search;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestNoticeListVo extends Search {
    /**
     * 标题
     */
    private String title;
    /**
     * 公告内容
     */
    private String noticeText;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
