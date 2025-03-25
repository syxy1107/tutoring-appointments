package cn.qutacm.dao;

import cn.qutacm.pojo.po.Activity;
import cn.qutacm.pojo.vo.ActivityVO;
import org.apache.ibatis.annotations.Mapper;
import org.checkerframework.checker.units.qual.A;

import java.util.Date;
import java.util.List;

/**
 *@Description:
 *@Author:@tianqi
 */
@Mapper
public interface ActivityDao {

    List<Activity> queryStartActivityByDay(Integer day);

    Integer queryCountByActivityId(Integer activityId);

    void decrCountByActivityId(Integer activityId);

    Activity getByStartAndEnd(Date startTime, Date endTime, Integer day);

    void delete(Activity activity);

    void insert(Activity activity);

    Activity getById(Integer activityId);

    void deleteById(Long id);

    List<Activity> findByDayBetweenAndStatus(Date start,Date end, int i);
}
