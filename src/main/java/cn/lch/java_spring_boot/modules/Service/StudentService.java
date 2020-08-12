package cn.lch.java_spring_boot.modules.Service;

import cn.lch.java_spring_boot.modules.common.vo.Result;
import cn.lch.java_spring_boot.modules.entity.Student;

public interface StudentService {
    Result<Student> insertStudent(Student student);
}
