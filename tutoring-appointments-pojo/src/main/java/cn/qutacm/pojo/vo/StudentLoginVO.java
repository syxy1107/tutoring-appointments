package cn.qutacm.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@Description:
 *@Author:@tianqi
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentLoginVO {
    /** 学号 */
    private String studentId;
    /** token */
    private String token;
}
