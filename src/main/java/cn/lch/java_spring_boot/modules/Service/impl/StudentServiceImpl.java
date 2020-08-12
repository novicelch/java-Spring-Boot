package cn.lch.java_spring_boot.modules.Service.impl;

import cn.lch.java_spring_boot.modules.Service.StudentService;
import cn.lch.java_spring_boot.modules.common.vo.Result;
import cn.lch.java_spring_boot.modules.entity.Student;
import cn.lch.java_spring_boot.modules.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    @Transactional
    public Result<Student> insertStudent(Student student) {
        studentRepository.saveAndFlush(student);
        return new Result<Student>(Result.ResultStatus.SUCCESS.status,"insert success",student);
    }
}
