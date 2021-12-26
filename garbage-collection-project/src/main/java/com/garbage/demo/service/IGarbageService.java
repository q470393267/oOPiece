package com.garbage.demo.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.garbage.demo.entity.Garbage;
import com.garbage.demo.entity.Transport;
import com.garbage.demo.vo.request.RequestGarbageListByTypeVo;
import com.garbage.demo.vo.request.RequestGarbageListVo;

/**
 * <p>
 * 垃圾入库表 服务类
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
public interface IGarbageService extends IService<Garbage> {
    IPage<Garbage> list(RequestGarbageListVo garbageListVo);

    IPage<Garbage> getByType(RequestGarbageListByTypeVo garbageListByTypeVo);

    IPage<Garbage> getByTransportId(Integer transportId,Integer limit,Integer page);

    Double getWeightByTransportId(Integer transportId);

    void removeAllTransportId();
}
