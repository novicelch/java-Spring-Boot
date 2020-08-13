package cn.lch.java_spring_boot.modules.Service;

import cn.lch.java_spring_boot.modules.common.vo.Result;
import cn.lch.java_spring_boot.modules.common.vo.SearchVo;
import cn.lch.java_spring_boot.modules.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    Result<Student> insertStudent(Student student);

    Page<Student> getStudentsBySearchVo(SearchVo searchVo);

    List<Student> getStudentByStudentName(String studentName,Integer cardId);

}
