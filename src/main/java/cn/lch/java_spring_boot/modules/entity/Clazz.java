package cn.lch.java_spring_boot.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "h_clazz")
public class Clazz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clazzId;

    private String schoolName;


//     ManyToOne：多方使用 joinClumn，创建外键，一方使用 mappedBy 属性
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)

//     JoinColumn：name：多方 h_clazz 表的外键 school_id
//     insertable、updatable：标识该属性是否参与插入和更新插入
    @JoinColumn(name = "school_id", insertable = false, updatable = false)

//    不序列化该字段，避免无限递归
    @JsonIgnore
    private School school;

//    ManyToMany：一方使用 JoinTable 注解，另一方配置 mappedBy 属性
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)

    /**
     * JoinTable：name 中间表表名
     *          ：joinColumns 该表 h_clazz 参与中间表的主键
     *          ：inverseJoinColumns 关联表 h_student 参与中间表的主键
     */
    @JoinTable(name = "h_clazz_student",
            joinColumns = @JoinColumn(name = "clazz_id"),
            inverseJoinColumns = @JoinColumn(name="student_id"))   //中间表
    private List<Student> students;

    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}