package cn.qutacm.service;

import cn.qutacm.pojo.dto.AdminLoginDto;
import cn.qutacm.pojo.po.Admin;

public interface AdminService {
    Admin login(AdminLoginDto adminLoginDto);
}
