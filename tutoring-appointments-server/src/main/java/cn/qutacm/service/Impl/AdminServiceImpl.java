package cn.qutacm.service.Impl;

import cn.qutacm.dao.AdminDao;
import cn.qutacm.pojo.dto.AdminLoginDto;
import cn.qutacm.pojo.po.Admin;
import cn.qutacm.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 *@Description:
 *@Author:@tianqi
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;
    @Override
    public Admin login(AdminLoginDto adminLoginDto) {
        String username=adminLoginDto.getUser();
        String password=adminLoginDto.getPassword();
        Admin admin=adminDao.getByName(username);

        if(admin==null){
            return null;
        }

//        password= DigestUtils.md5DigestAsHex(password.getBytes());
        if(!password.equals(admin.getPassword())){
            return null;
        }

        return admin;
    }
}
