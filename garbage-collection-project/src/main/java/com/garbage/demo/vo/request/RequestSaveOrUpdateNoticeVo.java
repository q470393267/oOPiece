package com.garbage.demo.vo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestSaveOrUpdateNoticeVo {
    private Integer id;
    /**
     * 标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String noticeText;

}
