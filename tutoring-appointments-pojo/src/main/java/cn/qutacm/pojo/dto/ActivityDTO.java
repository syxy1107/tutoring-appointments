package cn.qutacm.pojo.dto;

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
public class ActivityDTO {


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


}
