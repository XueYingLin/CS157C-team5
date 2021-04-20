package com.springboot.subway.controller.v1.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Admin {

    @GetMapping(value = {"/", "/login"})
    public ModelAndView login() {
        return new ModelAndView("login");
    }

}
