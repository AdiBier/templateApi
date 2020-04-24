package com.ab.templateApi.api;

import com.ab.templateApi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    final private UserServiceImpl userServiceImpl;

    @Autowired
    public HomeController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/")
    public String empty() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
