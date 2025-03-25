package cn.qutacm.dao;

import cn.qutacm.pojo.po.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 *@Description:
 *@Author:@tianqi
 */
@Mapper
public interface AdminDao {

    public Admin getByName(String username);
}
