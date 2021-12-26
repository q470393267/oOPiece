package com.garbage.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.garbage.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.garbage.demo.vo.request.RequestChangePwdVo;
import com.garbage.demo.vo.request.RequestUserListVo;
import com.garbage.demo.vo.response.ResponseUserListVo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lzf
 * @since 2020-10-15
 */
public interface IUserService extends IService<User> {

    List<User> queryList(String id);

    User queryByUsername(String username);

    User queryByTel(String tel);

    ResponseUserListVo getUserById(String userId);

    IPage<ResponseUserListVo> userList(RequestUserListVo userListVo,Integer userType);

    Boolean updateByType(User user);

    String avatarUpload(MultipartFile file) throws IOException;


}
