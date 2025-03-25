package cn.qutacm.service.Impl;

import cn.qutacm.dao.StudentDao;
import cn.qutacm.pojo.po.Student;
import cn.qutacm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *@Description:
 *@Author:@tianqi
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Override
    public Student getById(String studentId) {
        return studentDao.getByStudentId(studentId);
    }
}
