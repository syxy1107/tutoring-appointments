package cn.qutacm.handler;

import cn.qutacm.types.common.Response;
import cn.qutacm.types.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Response exceptionHandler(Exception ex){
        log.error("异常信息：{}", ex.getMessage());
        return  Response.builder()
                .data(ex.getMessage())
                .code(ResponseCode.UN_ERROR.getCode())
                .info(ResponseCode.UN_ERROR.getInfo())
                .build();
    }
    /**
     * 处理sql异常
     */
//    @ExceptionHandler
//    public Response exceptionalHander(SQLIntegrityConstraintViolationException ex){
//        log.error("异常信息：{}", ex.getMessage());
//        if(ex.getMessage().contains("Duplicate entry")){
//            String[] spilt=ex.getMessage().split(" ");
//            return Response.error(spilt[2]+ MessageConstant.ALREADY_EXISTS);
//        }
//            return Result.error(MessageConstant.UNKNOWN_ERROR);
//        }
}
