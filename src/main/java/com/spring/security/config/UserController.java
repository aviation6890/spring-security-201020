package com.spring.security.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users/status/check")
    public String userStatusCheck() {
        return "Authorized user";
    }

    @GetMapping("/manager/status/check")
    public String managerStatusCheck(){
        return "Authorized manager";
    }
}
