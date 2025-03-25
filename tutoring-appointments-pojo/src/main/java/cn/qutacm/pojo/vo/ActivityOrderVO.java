package cn.qutacm.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *@Description:
 *@Author:@tianqi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityOrderVO {

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 活动时间
     */
    private Date startTime;
    private Date endTime;
    /**
     * 创建时间
     */
    private Date createTime;

}
