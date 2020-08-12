package cn.lch.java_spring_boot.modules.Service;

import cn.lch.java_spring_boot.modules.common.vo.Result;
import cn.lch.java_spring_boot.modules.entity.Card;
import cn.lch.java_spring_boot.modules.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CardService {
    Result<Card> insertCard(Card card);
}
