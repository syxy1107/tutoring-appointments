package cn.qutacm.dao;

import cn.qutacm.pojo.po.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 *@Description:
 *@Author:@tianqi
 */
@Mapper
public interface StudentDao {

    public Integer queryCountByStudentId(String studentId);

    public void insert(Student student);

    Student getByStudentId(String studentId);
}
