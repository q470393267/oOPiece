package com.garbage.demo.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.garbage.demo.entity.User;
import com.garbage.demo.mapper.UserMapper;
import com.garbage.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.garbage.demo.vo.request.RequestChangePwdVo;
import com.garbage.demo.vo.request.RequestUserListVo;
import com.garbage.demo.vo.response.ResponseUserListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired IUserService userService;

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryList(String id) {
        return userMapper.queryList(id);
    }

    @Override
    public User queryByUsername(String username) {
        return userService.getOne(new QueryWrapper<User>().eq("username",username));
    }

    @Override
    public User queryByTel(String tel) {
        return userService.getOne(new QueryWrapper<User>().eq("tel",tel));
    }

    @Override
    public ResponseUserListVo getUserById(String userId) {
        User user = userService.getOne(new QueryWrapper<User>().eq("id",userId));

        ResponseUserListVo responseUserListVo = new ResponseUserListVo();
        if(ObjectUtils.isNotEmpty(user)){
            BeanUtils.copyProperties(user,responseUserListVo);
        }
        return responseUserListVo;

    }

    @Override
    public IPage<ResponseUserListVo> userList(RequestUserListVo userListVo,Integer userType) {
        IPage<User> userIPage = new Page<>(userListVo.getDisplayStart(),userListVo.getDisplayLength());

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.eq("type",userListVo.getType());

        if(ObjectUtils.isNotEmpty(userListVo.getUsername())){
            userQueryWrapper.like("username",userListVo.getUsername());
        }
        if(ObjectUtils.isNotEmpty(userListVo.getName())){
            userQueryWrapper.like("name",userListVo.getName());
        }
        if(ObjectUtils.isNotEmpty(userListVo.getTel())){
            userQueryWrapper.like("tel",userListVo.getTel());
        }
        if(ObjectUtils.isNotEmpty(userListVo.getAddress())){
            userQueryWrapper.like("address",userListVo.getAddress());
        }
        return page(userIPage,userQueryWrapper).convert(user -> {
            ResponseUserListVo r = new ResponseUserListVo();
            BeanUtils.copyProperties(user,r);
            r.setUserType(userType);
            return r;
        });
    }

    /**
     * 判断user现在的权限，如果为用户则升级为管理员，如果为管理员则降为普通用户
     * @param user
     * @return
     */
    @Override
    public Boolean updateByType(User user) {
        if(user.getType() == 0){
            user.setType(1);
            return userService.saveOrUpdate(user);
        }
        if(user.getType() == 1){
            user.setType(0);
            return userService.saveOrUpdate(user);
        }
        return false;
    }

    private String uploadPath = "D://images/";
    @Override
    public String avatarUpload(MultipartFile file) throws IOException {
        // 获取上传文件名
        String filename = file.getOriginalFilename();
        // 定义上传文件保存路径
        String avatarUrl = "avatar_images/";
        String path = uploadPath+avatarUrl;
        // 新建文件
        File filepath = new File(path, filename);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
            // 写入文件,File.separator代表斜杠
            // 127.0.0.1:8085/goodsImg/1.png
        file.transferTo(new File(path + File.separator + filename));

        return avatarUrl+filename;
    }


}
