package cn.qutacm.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@Description:
 *@Author:@tianqi
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentLoginDTO {
    /** 学号 */
    private String studentId;
    /** 密码 */
    private String password;
}
