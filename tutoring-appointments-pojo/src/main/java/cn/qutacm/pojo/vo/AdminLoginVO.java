package cn.qutacm.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@Description:
 *@Author:@tianqi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminLoginVO {
    private Long id;

    private String user;

    private String token;
}
