package com.cs157c.subway.controller.v1.ui;

import com.cs157c.subway.controller.v1.frontEndFormat.AgencyFormat;
import com.cs157c.subway.controller.v1.frontEndFormat.TrainFormat;
import com.cs157c.subway.controller.v1.frontEndFormat.PasswordFormat;
import com.cs157c.subway.controller.v1.frontEndFormat.ProfileFormat;
import com.cs157c.subway.controller.v1.frontEndFormat.TripFormat;
import com.cs157c.subway.dto.model.train.AgencyDto;
import com.cs157c.subway.dto.model.train.TrainDto;
import com.cs157c.subway.dto.model.train.StationDto;
import com.cs157c.subway.dto.model.train.TripDto;
import com.cs157c.subway.dto.model.user.UserDto;
import com.cs157c.subway.repository.train.AgencyRepository;
import com.cs157c.subway.repository.train.StationRepository;
import com.cs157c.subway.repository.train.TrainRepository;
import com.cs157c.subway.repository.train.TripRepository;
import com.cs157c.subway.repository.user.UserRepository;
import com.cs157c.subway.service.SubwayReservationService;
import com.cs157c.subway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private SubwayReservationService subwayReservationService;

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private StationRepository stationRepository;


    @Autowired
    private TripRepository tripRepository;


    @Autowired
    private UserRepository userRepository;


    @GetMapping(value = "/dashboard")
    public ModelAndView dashboard() throws Exception {
        ModelAndView modelAndView = new ModelAndView("dashboard");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", userDto);
        modelAndView.addObject("userName", userDto.getFullName());
        return modelAndView;
    }

    @GetMapping(value = "/agency")
    public ModelAndView agencyDetails() throws Exception {
        ModelAndView modelAndView = new ModelAndView("agency");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());
        AgencyDto agencyDto = subwayReservationService.getAgency(userDto);

        AgencyFormat agencyFormat = new AgencyFormat()
                .setAgencyName(agencyDto.getName())
                .setAgencyDetails(agencyDto.getDetails())
                .setContactInfo(agencyDto.getContactInfo())
                .setAddress(agencyDto.getAddress())
                .setOfficeHour(agencyDto.getOfficeHour());


        modelAndView.addObject("agencyFormData", agencyFormat);
        modelAndView.addObject("agency", agencyDto);
        modelAndView.addObject("userName", userDto.getFullName());
        return modelAndView;
    }





    @PostMapping(value = "/agency")
    public ModelAndView updateAgency(@Valid @ModelAttribute("agencyFormData") AgencyFormat agencyFormat) throws Exception {
        ModelAndView modelAndView = new ModelAndView("agency");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());
        AgencyDto agencyDto = subwayReservationService.getAgency(userDto);
        modelAndView.addObject("agency", agencyDto);
        modelAndView.addObject("userName", userDto.getFullName());

        if (agencyDto != null) {
            agencyDto.setName(agencyFormat.getAgencyName());
            agencyDto.setDetails(agencyFormat.getAgencyDetails());
            agencyDto.setContactInfo(agencyFormat.getContactInfo());
            agencyDto.setAddress(agencyFormat.getAddress());
            agencyDto.setOfficeHour(agencyFormat.getOfficeHour());
            subwayReservationService.updateAgency(agencyDto, null);
        }
        return modelAndView;
    }


    @GetMapping(value = "/train")
    public ModelAndView busDetails() throws Exception {
        ModelAndView modelAndView = new ModelAndView("train");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());
        AgencyDto agencyDto = subwayReservationService.getAgency(userDto);
        modelAndView.addObject("agency", agencyDto);
        modelAndView.addObject("trainFormData", new TrainFormat());
        modelAndView.addObject("userName", userDto.getFullName());
        return modelAndView;
    }





    @PostMapping(value = "/train")
    public ModelAndView addNewBus(@Valid @ModelAttribute("trainFormData") TrainFormat trainFormat) throws Exception {
        ModelAndView modelAndView = new ModelAndView("train");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());
        AgencyDto agencyDto = subwayReservationService.getAgency(userDto);
        modelAndView.addObject("userName", userDto.getFullName());
        modelAndView.addObject("agency", agencyDto);

        try {
            TrainDto trainDto = new TrainDto();
            trainDto.setCode(trainFormat.getCode());
            trainDto.setCapacity(trainFormat.getCapacity());
            trainDto.setMake(trainFormat.getMake());

            AgencyDto updatedAgencyDto = subwayReservationService.updateAgency(agencyDto, trainDto);
            modelAndView.addObject("agency", updatedAgencyDto);
            modelAndView.addObject("trainFormData", new TrainFormat());
        } catch (Exception ex) {

        }

        return modelAndView;
    }


    @GetMapping(value = "/trip")
    public ModelAndView tripDetails() throws Exception {
        ModelAndView modelAndView = new ModelAndView("trip");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());
        AgencyDto agencyDto = subwayReservationService.getAgency(userDto);
        Set<StationDto> stations = subwayReservationService.getAllStations();
        List<TripDto> trips = subwayReservationService.getAgencyTrips(agencyDto.getCode());
        modelAndView.addObject("agency", agencyDto);
        modelAndView.addObject("stations", stations);
        modelAndView.addObject("trips", trips);
        modelAndView.addObject("tripFormData", new TripFormat());
        modelAndView.addObject("userName", userDto.getFullName());
        return modelAndView;
    }





    @PostMapping(value = "/trip")
    public ModelAndView addNewTrip(@Valid @ModelAttribute("tripFormData") TripFormat tripFormat) throws Exception {
        ModelAndView modelAndView = new ModelAndView("trip");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());
        AgencyDto agencyDto = subwayReservationService.getAgency(userDto);
        Set<StationDto> stations = subwayReservationService.getAllStations();
        List<TripDto> trips = subwayReservationService.getAgencyTrips(agencyDto.getCode());

        modelAndView.addObject("stations", stations);
        modelAndView.addObject("agency", agencyDto);
        modelAndView.addObject("userName", userDto.getFullName());
        modelAndView.addObject("trips", trips);


        try {
            TripDto tripDto = new TripDto();
            tripDto.setBeginStationCode(tripFormat.getBeginStation());
            tripDto.setEndStationCode(tripFormat.getEndStation());
            tripDto.setTrainCode(tripFormat.getTrainCode());
            tripDto.setJourneyTime(tripFormat.getTripDuration());
            tripDto.setFare(tripFormat.getTripFare());
            tripDto.setAgencyCode(agencyDto.getCode());
            subwayReservationService.addTrip(tripDto);

            trips = subwayReservationService.getAgencyTrips(agencyDto.getCode());
            modelAndView.addObject("trips", trips);
            modelAndView.addObject("tripFormData", new TripFormat());
        } catch (Exception ex) {

        }

        return modelAndView;
    }


    @GetMapping(value = "/profile")
    public ModelAndView getUserProfile() throws Exception {
        ModelAndView modelAndView = new ModelAndView("profile");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());
        ProfileFormat profileFormat = new ProfileFormat()
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setPhoneNumber(userDto.getMobileNumber());
        PasswordFormat passwordFormat = new PasswordFormat()
                .setEmail(userDto.getEmail())
                .setPassword(userDto.getPassword());
        modelAndView.addObject("profileForm", profileFormat);
        modelAndView.addObject("passwordForm", passwordFormat);
        modelAndView.addObject("userName", userDto.getFullName());
        return modelAndView;
    }





    @PostMapping(value = "/profile")
    public ModelAndView updateProfile(@Valid @ModelAttribute("profileForm") ProfileFormat profileFormat) throws Exception {
        ModelAndView modelAndView = new ModelAndView("profile");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());
        PasswordFormat passwordFormat = new PasswordFormat()
                .setEmail(userDto.getEmail())
                .setPassword(userDto.getPassword());
        modelAndView.addObject("passwordForm", passwordFormat);
        modelAndView.addObject("userName", userDto.getFullName());

        userDto.setFirstName(profileFormat.getFirstName());
        userDto.setLastName(profileFormat.getLastName());
        userDto.setMobileNumber(profileFormat.getPhoneNumber());
        userService.updateProfile(userDto);
        modelAndView.addObject("userName", userDto.getFullName());

        return modelAndView;
    }





    @PostMapping(value = "/password")
    public ModelAndView changePassword(@Valid @ModelAttribute("passwordForm") PasswordFormat passwordFormat) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByEmail(auth.getName());

        ModelAndView modelAndView = new ModelAndView("profile");
        ProfileFormat profileFormat = new ProfileFormat()
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setPhoneNumber(userDto.getMobileNumber());
        modelAndView.addObject("profileForm", profileFormat);
        modelAndView.addObject("userName", userDto.getFullName());
        return modelAndView;
    }

}
