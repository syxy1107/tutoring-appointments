package cn.qutacm.service.Impl;

import cn.qutacm.dao.StudentDao;
import cn.qutacm.pojo.dto.StudentLoginDTO;
import cn.qutacm.pojo.dto.StudentRegistrationDTO;
import cn.qutacm.pojo.po.Student;
import cn.qutacm.service.RegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 *@Description:
 *@Author:@tianqi
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Resource
    private StudentDao studentDao;

    @Override
    public Integer registration(StudentRegistrationDTO studentRegistrationDTO) {
        String studentId=studentRegistrationDTO.getStudentId();
        Integer count = studentDao.queryCountByStudentId(studentId);
        if(count != 0){
//            throw new AppException(ResponseCode.ALREADY_REGISTRATION.getInfo(),ResponseCode.ALREADY_REGISTRATION.getCode());
            return 1;
        }
        String password=studentRegistrationDTO.getPassword();
        if(password.length()<8||password.length()>16){
            return 4;
        }
        boolean hasDigit = password.matches(".*\\d.*"); // 是否包含数字
        boolean hasUpperCase = password.matches(".*[A-Z].*"); // 是否包含大写字母
        boolean hasLowerCase = password.matches(".*[a-z].*"); // 是否包含小写字母

        if (!hasDigit || !hasUpperCase || !hasLowerCase) {
//            System.out.println("字符串包含数字、大写字母和小写字母。");
//            return false;
            return 2;
        }
        Student student=Student.builder()
                .name(studentRegistrationDTO.getName())
                .phoneNumber(studentRegistrationDTO.getPhoneNumber())
                .college(studentRegistrationDTO.getCollege())
                .createTime(new Date())
                .classes(studentRegistrationDTO.getClasses())
                .password(DigestUtils.md5DigestAsHex(studentRegistrationDTO.getPassword().getBytes()))
                .studentId(studentRegistrationDTO.getStudentId())
                .build();
        studentDao.insert(student);
        return 3;
    }

    @Override
    public Student login(StudentLoginDTO studentLoginDTO) {
        return studentDao.getByStudentId(studentLoginDTO.getStudentId());
    }
}
