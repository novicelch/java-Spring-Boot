package cn.lch.java_spring_boot.modules.Service.impl;

import cn.lch.java_spring_boot.modules.Service.StudentService;
import cn.lch.java_spring_boot.modules.common.vo.Result;
import cn.lch.java_spring_boot.modules.common.vo.SearchVo;
import cn.lch.java_spring_boot.modules.entity.Student;
import cn.lch.java_spring_boot.modules.repository.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Page<Student> getStudentsBySearchVo(SearchVo searchVo) {
        /*-------------------------------------------------------------------*/
        String orderBy = StringUtils.isBlank(searchVo.getOrderBy())? "studentId":searchVo.getOrderBy();
        /*-------------------------------------------------------------------*/
        Sort.Direction direction = StringUtils.isBlank(searchVo.getSort())||
                searchVo.getSort().equalsIgnoreCase("asc")?Sort.Direction.ASC:Sort.Direction.DESC;
        Sort sort = new Sort(direction,orderBy);
        /*-------------------------------------------------------------------*/
        Pageable pageable = PageRequest.of(searchVo.getCurrentPage()-1,searchVo.getPageSize(),sort);
        /*-------------------------------------------------------------------*/
        Student student = new Student();
        student.setStudentName(searchVo.getKeyWord());
        /*-------------------------------------------------------------------*/
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("studentName",match ->match.contains())
                .withIgnorePaths("studentId");
        Example<Student> example = Example.of(student,matcher);
        /*-------------------------------------------------------------------*/
        return studentRepository.findAll(example,pageable);
    }

    @Override
    public List<Student> getStudentByStudentName(String studentName,Integer cardId) {
        return Optional.ofNullable(studentRepository.findTop3ByStudentNameLike(String.format("%s%S%s","%",studentName,"%")))
                .orElse(Collections.emptyList());
    }
}