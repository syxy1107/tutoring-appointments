package cn.qutacm.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *@Description:
 *@Author:@tianqi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    /** 自增ID */
    private Long id;
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
    /** 创建日期 */
    private Date createTime;
}
