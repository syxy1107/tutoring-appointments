package cn.qutacm.dao;

import cn.qutacm.pojo.dto.PageReq;
import cn.qutacm.pojo.po.ActivityOrder;
import cn.qutacm.pojo.vo.ActivityOrderVO;
import cn.qutacm.pojo.vo.ActivityVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityOrderDao {


    void insert(ActivityOrder activityOrder);

    List<ActivityOrder> getByStudentId(String studentId);

    List<ActivityOrder> getByPage(Integer offset, Integer limit);
}
