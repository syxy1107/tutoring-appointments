package cn.qutacm.service.Impl;

import cn.qutacm.dao.ActivityDao;
import cn.qutacm.dao.ActivityOrderDao;
import cn.qutacm.pojo.dto.PageReq;
import cn.qutacm.pojo.po.Activity;
import cn.qutacm.pojo.po.ActivityOrder;
import cn.qutacm.pojo.vo.ActivityOrderVO;
import cn.qutacm.service.ActivityOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *@Description:
 *@Author:@tianqi
 */
@Service
public class ActivityOrderServiceImpl implements ActivityOrderService {

    @Resource
    private ActivityOrderDao activityOrderDao;
    @Resource
    private ActivityDao activityDao;

    @Override
    public List<ActivityOrderVO> getByPage(PageReq req) {
        Integer pageNo=req.getPageNo();
        Integer pageSize=req.getPageSize();
        Integer offset = (pageNo - 1) * pageSize;

        List<ActivityOrder> list = activityOrderDao.getByPage(offset,pageSize);
        List<ActivityOrderVO> list1 =new ArrayList<>();
        list.forEach(item->{
            Activity activity = activityDao.getById(item.getActivityId());
            ActivityOrderVO activityOrderVO=new ActivityOrderVO();
            activityOrderVO.setStartTime(activity.getStartTime());
            activityOrderVO.setEndTime(activity.getEndTime());
            activityOrderVO.setCreateTime(item.getCreateTime());
            activityOrderVO.setStudentId(item.getStudentId());
            list1.add(activityOrderVO);
        });
        return list1;
    }

    @Override
    public boolean appointment(ActivityOrder appointment) {
        return false;
    }

    @Override
    public List<ActivityOrderVO> queryActivityOrderByStudentId(String studentId) {

        List<ActivityOrder> student = activityOrderDao.getByStudentId(studentId);
        List<ActivityOrderVO> list=new ArrayList<>();
        student.forEach(item->{
            Activity activity=activityDao.getById(item.getActivityId());
            ActivityOrderVO activityOrderVO=ActivityOrderVO.builder()
                    .createTime(item.getCreateTime())
                    .endTime(activity.getEndTime())
                    .startTime(activity.getStartTime())
                    .studentId(item.getStudentId())
                    .build();
            list.add(activityOrderVO);
        });
        return list;
    }
}
