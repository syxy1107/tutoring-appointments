package cn.qutacm.controller.user;

import cn.qutacm.pojo.po.ActivityOrder;
import cn.qutacm.pojo.vo.ActivityOrderVO;
import cn.qutacm.pojo.vo.ActivityVO;
import cn.qutacm.service.ActivityOrderService;
import cn.qutacm.service.ActivityService;
import cn.qutacm.types.common.Response;
import cn.qutacm.types.context.BaseContext;
import cn.qutacm.types.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *@Description:
 *@Author:@tianqi
 */
@RestController
@Slf4j
@RequestMapping("/user/activity")
public class ActivityController {
    @Resource
    private ActivityService activityService;
    @Resource
    private ActivityOrderService activityOrderService;

    @GetMapping("/showActivity")
    public Response<List<ActivityVO>> showActivity(@RequestParam Integer day){
        List<ActivityVO> list =activityService.queryStartActivityByDay(day);
        return Response.<List<ActivityVO>>builder()
                .data(list)
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .build();
    }
    @PostMapping("/appointment")
    public Response<Boolean> appointment(@RequestBody ActivityOrder appointment){
        appointment.setStudentId(String.valueOf(BaseContext.getCurrentId()));
        boolean result= activityService.appointment(appointment);
        if(result){
            return Response.<Boolean>builder()
                    .data(result)
                    .info(ResponseCode.SUCCESS.getInfo())
                    .code(ResponseCode.SUCCESS.getCode())
                    .build();
        }
        return Response.<Boolean>builder()
                .data(result)
                .info(ResponseCode.UN_ERROR.getInfo())
                .code(ResponseCode.UN_ERROR.getCode())
                .build();
    }
    @GetMapping("/showMyActivity")
    public Response<List<ActivityOrderVO>> showActivity(){
        String studentId= String.valueOf(BaseContext.getCurrentId());
        List<ActivityOrderVO> list =activityOrderService.queryActivityOrderByStudentId(studentId);
        return Response.<List<ActivityOrderVO>>builder()
                .data(list)
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .build();
    }
}
