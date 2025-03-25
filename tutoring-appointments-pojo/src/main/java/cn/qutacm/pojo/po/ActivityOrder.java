package cn.qutacm.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 活动订单表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityOrder {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 活动ID
     */
    private Integer activityId;

    /**
     * 创建时间
     */
    private Date createTime;
}