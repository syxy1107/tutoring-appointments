package cn.qutacm.controller.user;

import cn.qutacm.pojo.dto.StudentLoginDTO;
import cn.qutacm.pojo.dto.StudentRegistrationDTO;
import cn.qutacm.pojo.po.Student;
import cn.qutacm.pojo.vo.StudentLoginVO;
import cn.qutacm.service.RegistrationService;
import cn.qutacm.service.StudentService;
import cn.qutacm.types.common.Response;
import cn.qutacm.types.context.BaseContext;
import cn.qutacm.types.enums.ResponseCode;
import cn.qutacm.types.properties.JwtProperties;
import cn.qutacm.types.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 *@Description:用户
 *@Author:@tianqi
 */
@RestController
@Slf4j
@RequestMapping("/user/user")
public class UserController {

    @Resource
    private RegistrationService registrationService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private StudentService studentService;

    @PostMapping("/registration")
    public Response<Boolean> registration(@RequestBody StudentRegistrationDTO studentRegistrationDTO){
        Integer result = registrationService.registration(studentRegistrationDTO);
        Response<Boolean> response=new Response<>();
        if(result==1){
            response.setCode(ResponseCode.ALREADY_REGISTRATION.getCode());
            response.setInfo(ResponseCode.ALREADY_REGISTRATION.getInfo());
            response.setData(false);
        }else if(result==2){
            response.setCode(ResponseCode.WEAK_PASSWORD_STRENGTH.getCode());
            response.setInfo(ResponseCode.WEAK_PASSWORD_STRENGTH.getInfo());
            response.setData(false);
        }else if(result==3){
            response.setCode(ResponseCode.SUCCESS.getCode());
            response.setInfo(ResponseCode.SUCCESS.getInfo());
            response.setData(true);
        }else {
            response.setCode(ResponseCode.ERROR_LENGTH.getCode());
            response.setInfo(ResponseCode.ERROR_LENGTH.getInfo());
            response.setData(false);
        }
        return response;
    }
    @PostMapping("/login")
    public Response<StudentLoginVO> login(@RequestBody StudentLoginDTO studentLoginDTO){
        Student student = registrationService.login(studentLoginDTO);
//        Admin admin = adminService.login(adminLoginDto);
        if(student==null){
            return Response.<StudentLoginVO>builder()
                    .code(ResponseCode.LOGIN_ERROR.getCode())
                    .info(ResponseCode.LOGIN_ERROR.getInfo())
                    .build();
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("StudentId", student.getStudentId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        StudentLoginVO studentLoginVO=StudentLoginVO.builder()
                .token(token)
                .studentId(student.getStudentId())
                .build();
        return Response.<StudentLoginVO>builder()
                .data(studentLoginVO)
                .info(ResponseCode.SUCCESS.getInfo())
                .code(ResponseCode.SUCCESS.getCode())
                .build();
    }
    @GetMapping("/myMessage")
    public Response<Student> myMessage(){
        String studentId= String.valueOf(BaseContext.getCurrentId());
        Student student = studentService.getById(studentId);
//        Admin admin = adminService.login(adminLoginDto);
        if(student==null){
            return Response.<Student>builder()
                    .code("0008")
                    .info("该账号不存在")
                    .build();
        }
        return Response.<Student>builder()
                .data(student)
                .info(ResponseCode.SUCCESS.getInfo())
                .code(ResponseCode.SUCCESS.getCode())
                .build();
    }

}
