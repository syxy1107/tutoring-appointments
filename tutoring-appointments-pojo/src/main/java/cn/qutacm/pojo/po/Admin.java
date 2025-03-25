package cn.qutacm.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String user;

    /**
     * 密码
     */
    private String password;
}