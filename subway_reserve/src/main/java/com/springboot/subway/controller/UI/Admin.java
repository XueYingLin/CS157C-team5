package com.springboot.subway.controller.UI;

import com.springboot.subway.model.user.User;
import com.springboot.subway.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Admin {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/login"})
    public ModelAndView login() {
        return new ModelAndView("login");
    }


    @RequestMapping(value = {"/signup"}, method= RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", new User());
        model.setViewName("/signup");

        return model;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String jumpToMain() {
        return "redirect:dashboard";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "redirect:dashboard";
    }




    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout() {
        return "redirect:login";
    }



//    @RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
//    public ModelAndView signup() {
//        ModelAndView modelAndView = new ModelAndView("signup");
//        modelAndView.addObject("signupData", new signupFormat());
//        return modelAndView;
//    }


//    @RequestMapping(value= {"/signup"}, method= RequestMethod.POST)
//    public ModelAndView createNewAdminUser(@Valid signupFormat signupData) throws Exception {
//        ModelAndView model = new ModelAndView("signup");
//
//        //transfer an user to userDTO
//        UserDto userDto = new UserDto();
//        userDto.setFirstName(signupData.getFirstName());
//        userDto.setLastName(signupData.getLastName());
//        userDto.setEmail(signupData.getEmail());
//        userDto.setPassword(signupData.getPassword());
//        userDto.setPhoneNumber(signupData.getPhoneNumber());
//        userDto.setAdmin(true);
//        UserDto admin = userService.register(userDto);
//
//
//        AgencyDto agencyDto = new AgencyDto();
//        agencyDto.setName(signupData.getAgencyName());
//        agencyDto.setDetails(signupData.getAgencyDetails());
//        agencyDto.setOwner();
//        model.addObject("user", new User());
//        model.setViewName("signup");
//
//        return model;
//    }



    @RequestMapping(value= {"/signup"}, method= RequestMethod.POST)
    public String createNewAdminUser(@Valid User user) throws Exception {
        ModelAndView model = new ModelAndView();

        userService.saveUser(user);
        model.addObject("user", new User());
        model.setViewName("signup");

        return  "redirect:login";
    }

}