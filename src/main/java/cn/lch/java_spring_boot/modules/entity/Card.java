package cn.lch.java_spring_boot.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "h_card")
public class Card {

    @Id
    //设置主键自动增长
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    @Column(name = "card_no", length = 33, unique = true)
    private String cardNo;


    //OneToOne：一对一关系中，一方使用 JoinColumn 注解（有外键），另一方使用 mappedBy 属性（可选）
    @OneToOne(mappedBy = "studentCard", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)

//    不序列化该字段，避免无限递归
    @JsonIgnore
    private Student student;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}