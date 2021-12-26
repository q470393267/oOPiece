package com.garbage.demo.controller;


import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.garbage.demo.common.Result;
import com.garbage.demo.entity.User;
import com.garbage.demo.service.IUserService;
import com.garbage.demo.utils.PhoneFormatCheckUtils;
import com.garbage.demo.utils.StringConst;
import com.garbage.demo.utils.VoUtilsTool;
import com.garbage.demo.vo.request.*;
import com.garbage.demo.vo.response.ResponseUserListVo;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private Producer producer;
    @Autowired
    private IUserService userService;

    @GetMapping("/queryList")
    public Result queryList(@RequestParam(value = "id",required = false) String userId) {
        return Result.getSuccess().setData(userService.queryList(userId));
    }

    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletRequest request, HttpServletResponse response )
            throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        System.out.println(text);
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        HttpSession session = request.getSession();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY,text);

        session.setMaxInactiveInterval(60);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }


    @RequestMapping("/register")
    public Result register(@RequestBody RequestRegisterVo registerVo) throws IOException {
        //System.out.println(registerVo);
       // String avatarUrl = userService.avatarUpload(registerVo.getAvatarFile());
        //用户数据的校验
        if(ObjectUtils.isNotEmpty(userService.queryByUsername(registerVo.getUsername()))){
            System.out.println(registerVo.getUsername());
            return  Result.getFailure().setMsg("用户名已存在！！！");
        }
        if(ObjectUtils.isNotEmpty(userService.queryByTel(registerVo.getTel()))){
            return  Result.getFailure().setMsg("用户名已存在！！！");
        }
        if(VoUtilsTool.checkObjFieldIsNull(registerVo)){
            return Result.getFailure().setMsg("输入数据为空！！！");
        }
        if(registerVo.getPassword().trim().toCharArray().length < 8){
            return  Result.getFailure().setMsg("密码位数必须大于8！！！");
        }
        if(!PhoneFormatCheckUtils.isPhoneLegal(registerVo.getTel())){
            return  Result.getFailure().setMsg("手机号格式正确！！！");
        }

        //用户数据拷贝
        User user = new User();
        BeanUtils.copyProperties(registerVo,user);
        user.setPassword(DigestUtil.md5Hex(registerVo.getPassword()));
        user.setId(IdUtil.simpleUUID());
        user.setAvatarUrl("\\goodsImg\\avatar.jpg");
        //存入数据库中
        if(userService.saveOrUpdate(user)){
            return Result.getSuccess().setMsg("注册成功!!!");
        }else{
            return  Result.getFailure().setMsg("注册失败!!!");
        }
    }

    @RequestMapping("/login")
    public Result login(HttpServletRequest request, @RequestBody RequestLoginVo loginRequestVo){
        //System.out.println(loginRequestVo);
        HttpSession session = request.getSession();
        String trueCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!trueCaptcha.equalsIgnoreCase(loginRequestVo.getCaptcha())){
            return Result.getFailure().setMsg(StringConst.CAPTCHA_ERROR);
        }
        User user;
        if(ObjectUtils.isEmpty(userService.queryByUsername(loginRequestVo.getUsernameOrTel()))){
             user = userService.queryByTel(loginRequestVo.getUsernameOrTel());
        }else{
            user = userService.queryByUsername(loginRequestVo.getUsernameOrTel());
        }
        if(ObjectUtils.isEmpty(user) || !DigestUtil.md5Hex(loginRequestVo.getPassword()).
                equals(user.getPassword())){
            return Result.getFailure().setMsg(StringConst.LOGIN_ERROR);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("userId",user.getId());
        result.put("userType",user.getType());

        return Result.getSuccess().setData(result);
    }

    @GetMapping("/getUserById/{userId}")
    public Result getUserById(@PathVariable String userId){
        return Result.getSuccess().setData(userService.getUserById(userId));
    }
    @DeleteMapping("/deleteByIds")
    public Result delete(@RequestBody RequestUserDeleteVo deleteVo){
        if(VoUtilsTool.checkObjFieldIsNull(deleteVo)){
            return Result.getFailure().setMsg(StringConst.DELETE_SELECT_ERROR);
        }
        if(userService.removeByIds(deleteVo.getStringIds())){
            return  Result.getSuccess().setMsg(StringConst.DELETE_SUCCESS);
        }else{
            return  Result.getFailure().setMsg(StringConst.DELETE_ERROR);
        }
    }
    @PostMapping("/list/{id}")
    public Result list(@RequestBody RequestUserListVo userListVo, @PathVariable String id){
        int type = userService.getById(id).getType();
        if(type== 0 || (type == 1 && userListVo.getType() == 1)){
            return Result.getFailure().setMsg("权限不足！！");
        }
        IPage<ResponseUserListVo> listVoIPage = userService.userList(userListVo,type);
        return Result.getSuccess().setData(listVoIPage);
    }

    @GetMapping("/updateByType/{userId}")
    public Result updateByType(@PathVariable String userId){
        if(userService.updateByType(userService.getById(userId))){
            return Result.getSuccess().setMsg("操作成功");
        }else{
            return Result.getFailure().setMsg("操作失败");
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody RequestUpdateUserVo requestUpdateUserVo){
        User user = userService.getById(requestUpdateUserVo.getId());
        BeanUtils.copyProperties(requestUpdateUserVo,user);
        if(userService.saveOrUpdate(user)){
            return Result.getSuccess().setMsg("修改成功");
        }else{
            return Result.getFailure().setMsg("修改失败");
        }
    }

    @PostMapping("/changePwd")
    public Result changePwd(@RequestBody RequestChangePwdVo requestChangePwdVo){
        User user = userService.getById(requestChangePwdVo.getId());
        if(!user.getPassword().equals(DigestUtil.md5Hex(requestChangePwdVo.getOldPassword()))){
            return Result.getFailure().setMsg("原密码错误");
        }
        if(requestChangePwdVo.getNewPassword().trim().toCharArray().length < 8){
            return  Result.getFailure().setMsg("密码位数必须大于8！！！");
        }
        user.setPassword(DigestUtil.md5Hex(requestChangePwdVo.getNewPassword()));
        if(userService.saveOrUpdate(user)){
            return Result.getSuccess().setMsg("修改成功");
        }else{
            return Result.getFailure().setMsg("修改失败");
        }
    }


    /**上传地址*/
    @Value("${file.upload.path}")
    private String uploadPath;
    /**
     * 上传图片
     * @param file
     */
    @RequestMapping("/upload")
    public Result upload(@RequestParam(value = "file") MultipartFile file,
                         @RequestParam(value = "userId") String id) {
        // 获取上传文件名
        String filename = file.getOriginalFilename();
        // 定义上传文件保存路径
        String path = uploadPath+"goodsImg/";
        // 新建文件
        File filepath = new File(path, filename);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            file.transferTo(new File(path + File.separator + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = userService.getById(id);
        user.setAvatarUrl("goodsImg/"+filename);
        userService.updateById(user);
        Map<String,String> img = new HashMap<>();
        img.put("src","goodsImg/"+filename);
        return Result.getSuccess().setData(img);
    }

    @GetMapping("/manage/{id}")
    public Result manage(@PathVariable String id){

        User user = userService.getById(id);

        if(user.getType() == 0){
            user.setType(1);
        }else if(user.getType() == 1){
            user.setType(0);
        }
        userService.updateById(user);

        return Result.getSuccess().setMsg("操作成功！！！");
    }

    @GetMapping("/resetPwd/{id}")
    public Result resetPwd(@PathVariable String id){
        User user = userService.getById(id);

        user.setPassword(DigestUtil.md5Hex("12345678"));

        userService.updateById(user);

        return Result.getSuccess().setMsg("操作成功！！！");
    }
}
