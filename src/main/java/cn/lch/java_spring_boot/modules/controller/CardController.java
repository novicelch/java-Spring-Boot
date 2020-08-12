package cn.lch.java_spring_boot.modules.controller;

import cn.lch.java_spring_boot.modules.Service.CardService;
import cn.lch.java_spring_boot.modules.common.vo.Result;
import cn.lch.java_spring_boot.modules.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {
    @Autowired
    private CardService cardService;

    /**
     *  http://localhost/card
     *  {"cardNo":"dfzjxfgkhlgdfsgl"}
     */
    @PostMapping(value = "/card",consumes = "application/json")
    public Result<Card> insertCard(@RequestBody Card card){
        return cardService.insertCard(card);
    }
}
