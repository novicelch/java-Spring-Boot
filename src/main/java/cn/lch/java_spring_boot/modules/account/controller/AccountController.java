package cn.lch.java_spring_boot.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    /**
     * http://localhost/account/login ---- get
     */
    @GetMapping("/login")
    public String loginPage() {
        return "indexSimple";
    }

    /**
     * http://localhost/account/register ---- get
     */
    @GetMapping("/register")
    public String registerPage() {
        return "indexSimple";
    }

    /**
     * http://localhost/account/users ---- get
     */
    @GetMapping("/users")
    public String usersPage() {
        return "index";
    }

    @GetMapping("/roles")
    public String rolesPage() {
        return "index";
    }
}