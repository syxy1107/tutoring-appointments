package cn.qutacm.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS("0000", "成功"),
    UN_ERROR("0001", "未知失败"),
    ILLEGAL_PARAMETER("0002", "非法参数"),
    ALREADY_REGISTRATION("0003","该用户已注册"),
    INCOMPLETE_INFORMATION("0004","注册信息不完整"),
    WEAK_PASSWORD_STRENGTH("0005","密码强度低，至少包含一个大写字母，一个小写字母和一个数字"),
    ERROR_LENGTH("0006","密码过长或过短，应在8-16个字符范围内"),
    LOGIN_ERROR("0007","登录失败，请检查账号密码后重试"),
    ;

    private String code;
    private String info;

}
