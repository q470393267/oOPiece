package com.garbage.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.garbage.demo.entity.Transport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.garbage.demo.vo.request.RequestTransportAddVo;
import com.garbage.demo.vo.request.RequestTransportListVo;
import com.garbage.demo.vo.response.ResponseTransportListVo;

/**
 * <p>
 * 运输表 服务类
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
public interface ITransportService extends IService<Transport> {

    boolean add(RequestTransportAddVo transportAddVo);

    IPage<ResponseTransportListVo> list(RequestTransportListVo transportListVo);

    void UpdateStatus();
}
