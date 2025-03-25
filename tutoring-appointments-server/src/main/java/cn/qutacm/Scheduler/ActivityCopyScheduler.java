package cn.qutacm.Scheduler;

import cn.qutacm.dao.ActivityDao;
import cn.qutacm.pojo.po.Activity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *@Description:
 *@Author:@tianqi
 */
@Component
public class ActivityCopyScheduler {
    @Autowired
    private ActivityDao activityRepository;

    /**
     * 获取本周一 00:00:00 的 Date 对象
     */
    public static Date getMondayStart() {
        Calendar cal = Calendar.getInstance();
        // 设置为本周一
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // 清除时、分、秒、毫秒
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取本周五 23:59:59 的 Date 对象
     */
    public static Date getFridayEnd() {
        Calendar cal = Calendar.getInstance();
        // 设置为本周五
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        // 设置时间为 23:59:59
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999); // 精确到毫秒
        return cal.getTime();
    }

    /**
     * 每周六 00:00 执行
     * cron 表达式: 秒 分 时 日 月 周
     */
    @Scheduled(cron = "0 0 0 ? * SAT") // 每周六零点
//    @Scheduled(cron = "0 * * * * ?") // 每分钟执行一次（测试用）
    @Transactional
    public void copyActivitiesToNextWeek() {
        Date mondayStart = getMondayStart();
        Date fridayEnd = getFridayEnd();

        System.out.println("周一 00:00:00: " + mondayStart);
        System.out.println("周五 23:59:59: " + fridayEnd);

        List<Activity> thisWeekActivities = activityRepository
                .findByDayBetweenAndStatus(mondayStart, fridayEnd, 1); // status=1 表示启用

        // 2. 复制并修改时间为下周
        for (Activity activity : thisWeekActivities) {
            Activity newActivity = new Activity();
            BeanUtils.copyProperties(activity, newActivity, "id"); // 复制除 id 外的所有字段

            // 修改时间为下周
            newActivity.setStartTime(addOneWeek(activity.getStartTime()));
            newActivity.setEndTime(addOneWeek(activity.getEndTime()));

            activityRepository.insert(newActivity);
        }
    }

    // 时间 +1 周
    private Date addOneWeek(Date date) {
        Instant instant = date.toInstant();
        return Date.from(instant.plus(7, ChronoUnit.DAYS));
    }
}
