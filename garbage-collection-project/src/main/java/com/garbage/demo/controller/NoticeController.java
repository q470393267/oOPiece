package com.garbage.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.garbage.demo.common.Result;
import com.garbage.demo.entity.Notice;
import com.garbage.demo.service.INoticeService;
import com.garbage.demo.utils.StringConst;
import com.garbage.demo.utils.VoUtilsTool;
import com.garbage.demo.vo.request.RequestDeleteVo;
import com.garbage.demo.vo.request.RequestNoticeListVo;
import com.garbage.demo.vo.request.RequestSaveOrUpdateNoticeVo;
import com.garbage.demo.vo.response.ResponseUserListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 公告表 前端控制器
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    INoticeService noticeService;

    @PostMapping("/saveOrUpdateNotice")
    public Result saveOrUpdateNotice(@RequestBody RequestSaveOrUpdateNoticeVo saveOrUpdateNoticeVo){
        if(ObjectUtils.isEmpty(saveOrUpdateNoticeVo.getTitle())){
            return Result.getFailure().setMsg(StringConst.NOTICE_IS_NULL);
        }
        String result = null;
        Notice notice;
        if(ObjectUtils.isEmpty(saveOrUpdateNoticeVo.getId())){
            notice = new Notice();
            BeanUtils.copyProperties(saveOrUpdateNoticeVo,notice);
            result = "添加";
        }else{
            notice = noticeService.getById(saveOrUpdateNoticeVo.getId());
            BeanUtils.copyProperties(saveOrUpdateNoticeVo,notice);
            result = "修改";
        }
        if(!noticeService.saveOrUpdate(notice)){
            return  Result.getFailure().setMsg(result +"失败了。");
        }
        return Result.getSuccess().setMsg(result + "成功啦！");
    }

    @DeleteMapping("/deleteByIds")
    public Result delete(@RequestBody RequestDeleteVo deleteVo){
        if(VoUtilsTool.checkObjFieldIsNull(deleteVo)){
            return Result.getFailure().setMsg(StringConst.DELETE_SELECT_ERROR);

        }
        if(noticeService.removeByIds(deleteVo.getIntegerIds())){
            return  Result.getSuccess().setMsg(StringConst.DELETE_SUCCESS);
        }else{
            return  Result.getFailure().setMsg(StringConst.DELETE_ERROR);
        }
    }

    @PostMapping("/list")
    public Result noticeList(@RequestBody RequestNoticeListVo noticeListVo){
        IPage<Notice> listVoIPage = noticeService.noticeList(noticeListVo);
        return Result.getSuccess().setData(listVoIPage);
    }

    @GetMapping("/getById/{id}")
    public Result getById( @PathVariable Integer id){
        return Result.getSuccess().setData(noticeService.getById(id));
    }

}
