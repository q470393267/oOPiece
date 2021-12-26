package com.garbage.demo.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.garbage.demo.common.Result;
import com.garbage.demo.entity.Garbage;
import com.garbage.demo.entity.Transport;
import com.garbage.demo.service.IGarbageService;
import com.garbage.demo.service.ITransportService;
import com.garbage.demo.vo.request.RequestTransportAddVo;
import com.garbage.demo.vo.request.RequestTransportListVo;
import com.garbage.demo.vo.response.ResponseTransportListVo;
//import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;

/**
 * <p>
 * 运输表 前端控制器
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
@RestController
@RequestMapping("/transport")
public class TransportController {
    @Autowired
    ITransportService transportService;
    @Autowired
    IGarbageService garbageService;

    @PostMapping("/add")
    public Result add(@RequestBody RequestTransportAddVo transportAddVo){
        if(transportService.add(transportAddVo)){
            return Result.getSuccess().setMsg("提交成功！！！");
        }
        return Result.getFailure().setMsg("提交失败！！！");
    }

    @PostMapping("/list")
    public Result list(@RequestBody RequestTransportListVo transportListVo){
        IPage<ResponseTransportListVo> page = transportService.list(transportListVo);
        return Result.getSuccess().setData(page);
    }

    @GetMapping("/packaging/{id}")
    @Transactional
    public Result packaging(@PathVariable Integer id){
        UpdateWrapper<Garbage> updateWrapper = new UpdateWrapper<>();

        updateWrapper.eq("transport_id",id);
        updateWrapper.set("status",1);

        garbageService.update(updateWrapper);
        UpdateWrapper<Transport> transportUpdateWrapper = new UpdateWrapper<>();

        transportUpdateWrapper.eq("id",id);
        transportUpdateWrapper.set("status",3);
        transportService.update(transportUpdateWrapper);
        return Result.getSuccess().setMsg("已运输");
    }

    @GetMapping("/passOrRefuse/{b}/{id}")
    public Result passOrRefuse(@PathVariable Boolean b, @PathVariable Integer id){
        UpdateWrapper<Transport> transportUpdateWrapper = new UpdateWrapper<>();

        transportUpdateWrapper.eq("id",id);
        if(b){
            transportUpdateWrapper.set("status",1);
        }else{
            transportUpdateWrapper.set("status",2);
        }
        transportService.update(transportUpdateWrapper);
        return Result.getSuccess().setMsg("操作成功");
    }


}
