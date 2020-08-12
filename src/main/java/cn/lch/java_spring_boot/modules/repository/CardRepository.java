package cn.lch.java_spring_boot.modules.repository;


import cn.lch.java_spring_boot.modules.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

}
