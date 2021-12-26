package com.garbage.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.garbage.demo.common.Result;
import com.garbage.demo.entity.Garbage;
import com.garbage.demo.service.IGarbageService;
import com.garbage.demo.utils.StringConst;
import com.garbage.demo.vo.request.RequestDeleteVo;
import com.garbage.demo.vo.request.RequestGarbageAddVo;
import com.garbage.demo.vo.request.RequestGarbageListByTypeVo;
import com.garbage.demo.vo.request.RequestGarbageListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 垃圾入库表 前端控制器
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
@RestController
@RequestMapping("/garbage")
public class GarbageController {

    @Autowired
    private IGarbageService garbageService;

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RequestGarbageAddVo requestGarbageAddVo){
        String result;
        if(requestGarbageAddVo.getWeight()<0){
            return Result.getFailure().setMsg("重量输入错误！！");
        }
        if(ObjectUtils.isNotEmpty(requestGarbageAddVo.getId())){
            result = "修改";
        }else{
            result = "添加";
        }
        Garbage garbage = new Garbage();
        BeanUtils.copyProperties(requestGarbageAddVo,garbage);
        if(garbageService.saveOrUpdate(garbage)){
            return Result.getSuccess().setMsg(result + "成功！！！");
        }else{
            return Result.getFailure().setMsg(result + "失败！！！");
        }
    }


    @DeleteMapping("/deleteByIds")
    public Result delete(@RequestBody RequestDeleteVo requestDeleteVo){
        if(ObjectUtils.isNotEmpty(requestDeleteVo.getIntegerIds())){
            QueryWrapper<Garbage> garbageQueryWrapper = new QueryWrapper<>();

            garbageQueryWrapper.isNull("transport_id");
            garbageQueryWrapper.in("id",requestDeleteVo.getIntegerIds());

            garbageService.remove(garbageQueryWrapper);
            return Result.getSuccess().setMsg(StringConst.DELETE_SUCCESS);
        }
        return Result.getFailure().setMsg(StringConst.DELETE_ERROR);
    }


    @PostMapping("/list")
    public Result list(@RequestBody RequestGarbageListVo garbageListVo){
        IPage<Garbage> garbageIPage = garbageService.list(garbageListVo);
        return Result.getSuccess().setData(garbageIPage);
    }

    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Integer id){
        return Result.getSuccess().setData(garbageService.getById(id));
    }

    @PostMapping("/getByType")
    public Result getByType(@RequestBody RequestGarbageListByTypeVo garbageListByTypeVo){
        IPage<Garbage> garbageIPage = garbageService.getByType(garbageListByTypeVo);
        return Result.getSuccess().setData(garbageIPage);
    }

    @GetMapping("/getByTransportId/{transportId}")
    public Result getByTransportId(@PathVariable Integer transportId
            ,@RequestParam(value = "limit") Integer limit,@RequestParam(value = "page") Integer page){
        IPage<Garbage> garbageIPage = garbageService.getByTransportId(transportId,limit ,page);
        return Result.getSuccess().setData(garbageIPage);
    }


}