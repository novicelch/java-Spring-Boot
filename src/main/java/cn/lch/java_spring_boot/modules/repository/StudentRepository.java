package cn.lch.java_spring_boot.modules.repository;

import cn.lch.java_spring_boot.modules.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{
}
