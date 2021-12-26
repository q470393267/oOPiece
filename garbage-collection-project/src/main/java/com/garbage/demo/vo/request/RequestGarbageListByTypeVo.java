package com.garbage.demo.vo.request;

import com.garbage.demo.common.Search;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestGarbageListByTypeVo extends Search {

    private Integer type;
    private String entryTime;
}
