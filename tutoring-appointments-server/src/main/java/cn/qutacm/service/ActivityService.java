package cn.qutacm.service;

import cn.qutacm.pojo.dto.ActivityDTO;
import cn.qutacm.pojo.po.ActivityOrder;
import cn.qutacm.pojo.vo.ActivityVO;

import java.util.List;

public interface ActivityService {
    List<ActivityVO> queryStartActivityByDay(Integer day);

    boolean appointment(ActivityOrder appointment);

    Integer delete(ActivityDTO activityDTO);

    Integer add(ActivityDTO activityDTO);


    Integer deleteById(Long id);
}
