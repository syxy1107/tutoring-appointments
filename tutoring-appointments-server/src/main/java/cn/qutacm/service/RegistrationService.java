package cn.qutacm.service;

import cn.qutacm.pojo.dto.StudentLoginDTO;
import cn.qutacm.pojo.dto.StudentRegistrationDTO;
import cn.qutacm.pojo.po.Student;

public interface RegistrationService {
    Integer registration(StudentRegistrationDTO studentRegistrationDTO);

    Student login(StudentLoginDTO studentLoginDTO);
}
