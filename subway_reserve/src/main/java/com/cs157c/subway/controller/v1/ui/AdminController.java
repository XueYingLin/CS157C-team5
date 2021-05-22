package com.cs157c.subway.controller.v1.ui;


import com.cs157c.subway.controller.v1.frontEndFormat.AdminRegisterFrontEndFormat;
import com.cs157c.subway.dto.model.train.AgencyDto;
import com.cs157c.subway.dto.model.user.UserDto;
import com.cs157c.subway.service.SubwayReservationService;
import com.cs157c.subway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class AdminController {

    @Autowired
    SubwayReservationService subwayReservationService;


    @Autowired
    private UserService userService;


    @GetMapping(value = {"/", "/login"})
    public ModelAndView login() {
        return new ModelAndView("login");
    }


    @GetMapping(value = {"/logout"})
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:login";
    }


    @GetMapping(value = "/home")
    public String home() {
        return "redirect:dashboard";
    }


    @GetMapping(value = "/signup")
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject("adminRegisterFrontEndFormatData", new AdminRegisterFrontEndFormat());
        return modelAndView;
    }




    @PostMapping(value = "/signup")
    public ModelAndView createNewAdmin(@Valid @ModelAttribute("adminRegisterFrontEndFormatData") AdminRegisterFrontEndFormat adminRegisterRequest) {
        ModelAndView modelAndView = new ModelAndView("signup");

        try {
            UserDto newUser = new UserDto();
            newUser.setEmail(adminRegisterRequest.getEmail());
            newUser.setPassword(adminRegisterRequest.getPassword());
            newUser.setFirstName(adminRegisterRequest.getFirstName());
            newUser.setLastName(adminRegisterRequest.getLastName());
            newUser.setMobileNumber(adminRegisterRequest.getPhoneNumber());
            newUser.setAdmin(true);
            UserDto admin = userService.signup(newUser); //register the admin
            AgencyDto agencyDto = new AgencyDto();
            agencyDto.setName(adminRegisterRequest.getAgencyName());
            agencyDto.setDetails(adminRegisterRequest.getAgencyDetails());
            agencyDto.setOwner(admin);
            subwayReservationService.addAgency(agencyDto);

        } catch (Exception exception) {
            return modelAndView;
        }

        return new ModelAndView("login");
    }

}
