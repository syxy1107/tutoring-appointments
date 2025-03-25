package cn.qutacm.service;

import cn.qutacm.pojo.dto.PageReq;
import cn.qutacm.pojo.po.ActivityOrder;
import cn.qutacm.pojo.vo.ActivityOrderVO;

import java.util.List;

public interface ActivityOrderService {
    List<ActivityOrderVO> getByPage(PageReq req);

    boolean appointment(ActivityOrder appointment);

    List<ActivityOrderVO> queryActivityOrderByStudentId(String studentId);
}
