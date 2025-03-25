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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageReq {
    private Integer pageSize;
    private Integer pageNo;
}
