package com.garbage.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.garbage.demo.entity.Garbage;
import com.garbage.demo.entity.Transport;
import com.garbage.demo.mapper.TransportMapper;
import com.garbage.demo.service.IGarbageService;
import com.garbage.demo.service.ITransportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.garbage.demo.vo.request.RequestTransportAddVo;
import com.garbage.demo.vo.request.RequestTransportListVo;
import com.garbage.demo.vo.response.ResponseTransportListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 运输表 服务实现类
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
@Service
public class TransportServiceImpl extends ServiceImpl<TransportMapper, Transport> implements ITransportService {

    @Autowired
    IGarbageService garbageService;
    @Autowired
    ITransportService transportService;


    @Override
    @Transactional
    public boolean add(RequestTransportAddVo transportAddVo) {
        Transport transport = new Transport();

        BeanUtils.copyProperties(transportAddVo,transport);

        transportService.save(transport);

        //更新垃圾列表的运输id
        UpdateWrapper<Garbage> wrapper = new UpdateWrapper<>();

        wrapper.in("id",transportAddVo.getGarbageIds());

        wrapper.set("transport_id",transport.getId());

        return garbageService.update(wrapper);
    }

    @Override
    public IPage<ResponseTransportListVo> list(RequestTransportListVo transportListVo) {
        IPage<Transport> page = new Page<>(transportListVo.getDisplayStart(),
                transportListVo.getDisplayLength());
        QueryWrapper<Transport> wrapper = new QueryWrapper<>();
        if(ObjectUtils.isNotEmpty(transportListVo.getType())){
            wrapper.eq("type",transportListVo.getType());
        }
        if(ObjectUtils.isNotEmpty(transportListVo.getOutTime())){
            wrapper.eq("out_time",transportListVo.getOutTime());
        }
        if(ObjectUtils.isNotEmpty(transportListVo.getStatus())){
            wrapper.eq("status",transportListVo.getStatus());
        }

        return transportService.page(page,wrapper).convert(transport->{
            ResponseTransportListVo listVo = new ResponseTransportListVo();
            BeanUtils.copyProperties(transport,listVo);
            listVo.setWeight(garbageService.getWeightByTransportId(transport.getId()));
            return listVo;
        });

    }

    @Autowired
    TransportMapper transportMapper;
    @Override
    public void UpdateStatus() {
        transportMapper.updateStatus();
    }


}
