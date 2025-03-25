package cn.qutacm.service.Impl;

import cn.qutacm.dao.ActivityDao;
import cn.qutacm.dao.ActivityOrderDao;
import cn.qutacm.pojo.dto.ActivityDTO;
import cn.qutacm.pojo.po.Activity;
import cn.qutacm.pojo.po.ActivityOrder;
import cn.qutacm.pojo.vo.ActivityVO;
import cn.qutacm.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 *@Description:
 *@Author:@tianqi
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityDao activityDao;
    @Resource
    private ActivityOrderDao activityOrderDao;
    @Override
    public List<ActivityVO> queryStartActivityByDay(Integer day) {
        List<Activity> activities = activityDao.queryStartActivityByDay(day);
        List<ActivityVO> list=new ArrayList<>();
        activities.forEach(item->{
            ActivityVO activityVO=ActivityVO.builder()
                    .startTime(item.getStartTime())
                    .activityId(Long.valueOf(item.getId()))
                    .endTime(item.getEndTime())
                    .build();
            list.add(activityVO);
        });
        return list;
    }

    @Override
    public boolean appointment(ActivityOrder activityOrder) {
        Integer count =activityDao.queryCountByActivityId(activityOrder.getActivityId());
        if(count == null||count==0) {
            return false;
//            throw new AppException("0004","该时间段已被预约");
        }
        activityDao.decrCountByActivityId(activityOrder.getActivityId());
        activityOrder.setCreateTime(new Date());
        activityOrderDao.insert(activityOrder);
        return true;
    }

    @Override
    public Integer delete(ActivityDTO activityDTO) {
        Date startTime = activityDTO.getStartTime();
        Date endTime = activityDTO.getEndTime();
        Integer day=activityDTO.getDay();
        Activity activity= activityDao.getByStartAndEnd(startTime,endTime,day);
        if(activity==null)return 0;
        activityDao.delete(activity);
        return 1;
    }

    @Override
    public Integer add(ActivityDTO activityDTO) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(activityDTO.getStartTime());

        Integer dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        Activity activity=Activity.builder()
                .personCount(activityDTO.getPersonCount())
                .status(1)
                .day(dayOfWeek-1)
                .endTime(activityDTO.getEndTime())
                .startTime(activityDTO.getStartTime())
                .build();
        activityDao.insert(activity);
        return 1;
    }

    @Override
    public Integer deleteById(Long id) {
        activityDao.deleteById(id);
        return 1;
    }


}
