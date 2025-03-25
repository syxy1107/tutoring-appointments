package cn.qutacm.controller.admin;

import cn.qutacm.dao.ActivityOrderDao;
import cn.qutacm.pojo.dto.*;
import cn.qutacm.pojo.po.ActivityOrder;
import cn.qutacm.pojo.po.Admin;
import cn.qutacm.pojo.vo.ActivityOrderVO;
import cn.qutacm.pojo.vo.ActivityVO;
import cn.qutacm.pojo.vo.AdminLoginVO;
import cn.qutacm.service.ActivityOrderService;
import cn.qutacm.service.ActivityService;
import cn.qutacm.service.AdminService;
import cn.qutacm.types.common.Response;
import cn.qutacm.types.enums.ResponseCode;
import cn.qutacm.types.properties.JwtProperties;
import cn.qutacm.types.utils.JwtUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Description:
 *@Author:@tianqi
 */
@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;
    @Autowired
    private JwtProperties jwtProperties;
    @Resource
    private ActivityService activityService;
    @Resource
    private ActivityOrderService activityOrderService;

    @PostMapping("/login")
    public Response<AdminLoginVO> login(@RequestBody AdminLoginDto adminLoginDto){
        Admin admin = adminService.login(adminLoginDto);
        if(admin==null){
            return Response.<AdminLoginVO>builder()
                    .code(ResponseCode.LOGIN_ERROR.getCode())
                    .info(ResponseCode.LOGIN_ERROR.getInfo())
                    .build();
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("user", admin.getUser());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        AdminLoginVO adminLoginVO=AdminLoginVO.builder()
                .token(token)
                .user(admin.getUser())
                .id(admin.getId())
                .build();
        return Response.<AdminLoginVO>builder()
                .data(adminLoginVO)
                .info(ResponseCode.SUCCESS.getInfo())
                .code(ResponseCode.SUCCESS.getCode())
                .build();
    }
    @PostMapping("showActivity")
    public Response<List<ActivityVO>> showActivity(@RequestBody ActivityRequestDTO activityRequestDTO){
        List<ActivityVO> list =activityService.queryStartActivityByDay(activityRequestDTO.getDay());
        return Response.<List<ActivityVO>>builder()
                .data(list)
                .code(ResponseCode.SUCCESS.getCode())
                .info(ResponseCode.SUCCESS.getInfo())
                .build();
    }
    @PostMapping("/delete")
    public Response<Boolean> delete(@RequestBody ActivityDeleteDTO activityDTO){
        Integer result= activityService.deleteById(activityDTO.getId());
        if(result==0){
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .data(false)
                    .build();
        }else {
            return Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(true)
                    .build();
        }
    }
    @PostMapping("/add")
    public Response<Boolean> add(@RequestBody ActivityDTO activityDTO){
        Integer result= activityService.add(activityDTO);
        if(result==0){
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .data(false)
                    .build();
        }else {
            return Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(true)
                    .build();
        }
    }
    @PostMapping("/page")
    public Response<List<ActivityOrderVO>> page(@RequestBody PageReq req){
        List<ActivityOrderVO> list= activityOrderService.getByPage(req);
        return Response.<List<ActivityOrderVO>>builder()
                .info("0000")
                .code("0000")
                .data(list)
                .build();
    }

}
