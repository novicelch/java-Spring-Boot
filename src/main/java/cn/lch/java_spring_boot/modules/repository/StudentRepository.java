package cn.lch.java_spring_boot.modules.repository;

import cn.lch.java_spring_boot.modules.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

    List<Student> findByStudentName(String studentName);

    List<Student> findByStudentNameLike(String studentName);

    List<Student> findTop3ByStudentNameLike(String studentName);

    //    @Query(value = "select s from Student s where s.studentName = ?1 and s.studentCard.cardId = ?2")
    @Query(nativeQuery = true, value = "select * from h_student where student_name = :studentName " +
                    "and card_id = :cardId")
    List<Student> getStudentsByParams(@Param("studentName") String studentName,
                                      @Param("cardId") int cardId);
}
