package cn.qutacm.pojo.po;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *@Description:
 *@Author:@tianqi
 */
/**
 * 活动表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Activity {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 周几
     */
    private Integer day;

    /**
     * 开启时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 人数
     */
    private Integer personCount;

    /**
     * 是否启用（1:启用, 0:禁用）
     */
    private Integer status;
}