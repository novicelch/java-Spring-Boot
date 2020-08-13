package cn.lch.java_spring_boot.modules.controller;

import cn.lch.java_spring_boot.modules.Service.StudentService;
import cn.lch.java_spring_boot.modules.common.vo.Result;
import cn.lch.java_spring_boot.modules.common.vo.SearchVo;
import cn.lch.java_spring_boot.modules.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     *  http://localhost/student
     *  {"studentName":"liuchenhu","studentCard":{"cardId":"1"}}
     */
    @PostMapping(value = "/student",consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student){
        return studentService.insertStudent(student);
    }

    /**
     *   http://localhost/students ---- post
     * {"currentPage":"1","pageSize":"5","keyWord":"liu","orderBy":"studentName","sort":"desc"}
     */
    @PostMapping(value = "/students", consumes = "application/json")
    public Page<Student> getStudentsBySearchVo(@RequestBody SearchVo searchVo){
        return studentService.getStudentsBySearchVo(searchVo);
    }

    /**
     *  http://localhost/students?studentName=liu ---- get
     */
    @GetMapping("/students")
    public List<Student> getStudentsBy(
            @RequestParam String studentName,
            @RequestParam(required = false, defaultValue = "0") Integer cardId) {
        return studentService.getStudentByStudentName(studentName,cardId);
    }
}