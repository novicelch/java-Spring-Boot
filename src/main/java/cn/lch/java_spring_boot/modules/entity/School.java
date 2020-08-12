package cn.lch.java_spring_boot.modules.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "h_school")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolId;
    private int schoolName;

    //实体 Bean 的属性不参与到数据库的映射
    @Transient
    private String region;

    /**
     * OneToMany：多方使用 joinClumn，创建外键，一方使用 mappedBy 属性
     */
    @OneToMany(mappedBy = "school", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<Clazz> clazzes;

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(int schoolName) {
        this.schoolName = schoolName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<Clazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<Clazz> clazzes) {
        this.clazzes = clazzes;
    }
}
