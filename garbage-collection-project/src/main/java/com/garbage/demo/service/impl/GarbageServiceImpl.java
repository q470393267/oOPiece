package com.garbage.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.garbage.demo.entity.Garbage;
import com.garbage.demo.entity.Transport;
import com.garbage.demo.mapper.GarbageMapper;
import com.garbage.demo.service.IGarbageService;
import com.garbage.demo.service.ITransportService;
import com.garbage.demo.vo.request.RequestGarbageListByTypeVo;
import com.garbage.demo.vo.request.RequestGarbageListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


/**
 * <p>
 * 垃圾入库表 服务实现类
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
@Service
public class GarbageServiceImpl extends ServiceImpl<GarbageMapper, Garbage> implements IGarbageService {

    @Autowired
    IGarbageService garbageService;
    @Override
    public IPage<Garbage> list(RequestGarbageListVo garbageListVo) {
        IPage<Garbage> garbageIPage = new Page<>(garbageListVo.getDisplayStart(),
                garbageListVo.getDisplayLength());

        QueryWrapper<Garbage> wrapper = new QueryWrapper<>();

        if(ObjectUtils.isNotEmpty(garbageListVo.getType())){
            wrapper.eq("type",garbageListVo.getType());
        }

        if(ObjectUtils.isNotEmpty(garbageListVo.getStatus())){
            wrapper.eq("status",garbageListVo.getStatus());
        }

        if(ObjectUtils.isNotEmpty(garbageListVo.getEntryTime())){
            wrapper.like("create_time",garbageListVo.getEntryTime());
        }

        return page(garbageIPage,wrapper);
    }

    @Override
    public IPage<Garbage> getByType(RequestGarbageListByTypeVo garbageListByTypeVo) {
        IPage<Garbage> garbageIPage = new Page<>(garbageListByTypeVo.getDisplayStart()
                ,garbageListByTypeVo.getDisplayLength());
        QueryWrapper<Garbage> wrapper = new QueryWrapper<>();
        wrapper.eq("type",garbageListByTypeVo.getType());
        wrapper.eq("status",0);
        wrapper.isNull("transport_id");
        if(ObjectUtils.isNotEmpty(garbageListByTypeVo.getEntryTime())){
            String startTime = "";
            String endTime = "";
            String str = garbageListByTypeVo.getEntryTime();
            char[] arr = str.toCharArray();

            for(int i = 0; i<arr.length;i++){
                if(arr[i] == ' '){
                    i+=2;
                }else if(startTime.length()<10){
                    startTime += arr[i];
                }else if(endTime.length()<10){
                    endTime += arr[i];
                }
            }
            wrapper.between("create_time",startTime,endTime);
        }
        return page(garbageIPage,wrapper);
    }

    @Override
    public IPage<Garbage> getByTransportId(Integer transportId, Integer limit, Integer page) {
        IPage<Garbage> garbageIPage = new Page<>(page,limit);
        QueryWrapper<Garbage> wrapper = new QueryWrapper<>();
        wrapper.eq("transport_id",transportId);
        return page(garbageIPage,wrapper);
    }

    @Override
    public Double getWeightByTransportId(Integer transportId) {
        QueryWrapper<Garbage> wrapper = new QueryWrapper<>();
        wrapper.eq("transport_id",transportId);
        ArrayList<Garbage> list = (ArrayList<Garbage>)garbageService.list(wrapper);
        Double weight = 0.0;
        for (Garbage g :list){
            weight += g.getWeight();
        }
        return weight;
    }

    @Autowired
    ITransportService transportService;

    @Override
    public void removeAllTransportId() {
        QueryWrapper<Transport> tq = new QueryWrapper<>();
        tq.eq("status",2);
        ArrayList<Transport> ts = (ArrayList<Transport>)transportService.list(tq);
        if(ts.isEmpty()){
            return;
        }
        ArrayList<Integer> ids = new ArrayList<>();
        for(Transport t :ts){
            ids.add(t.getId());
        }
        UpdateWrapper<Garbage> garbageUpdateWrapper = new UpdateWrapper<>();

        garbageUpdateWrapper.set("transport_id", null);
        garbageUpdateWrapper.in("transport_id",ids);
        garbageService.update(garbageUpdateWrapper);

    }


}

