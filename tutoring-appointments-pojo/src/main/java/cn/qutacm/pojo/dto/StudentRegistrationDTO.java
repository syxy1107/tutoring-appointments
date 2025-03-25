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
public class StudentRegistrationDTO {
    /** 姓名 */
    private String name;
    /** 学院 */
    private String college;
    /** 班级 */
    private String classes;
    /** 学号 */
    private String studentId;
    /** 手机号 */
    private String phoneNumber;
    /** 密码 */
    private String password;

}
