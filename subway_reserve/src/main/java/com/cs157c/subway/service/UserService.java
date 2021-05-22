package com.cs157c.subway.service;

import com.cs157c.subway.dto.mapper.UserMapper;
import com.cs157c.subway.dto.model.user.UserDto;
import com.cs157c.subway.model.user.Role;
import com.cs157c.subway.model.user.User;
import com.cs157c.subway.model.user.UserRoles;
import com.cs157c.subway.repository.user.RoleRepository;
import com.cs157c.subway.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;




@Component
public class UserService{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubwayReservationService subwayReservationService;

    @Autowired
    private ModelMapper modelMapper;


    public UserDto signup(UserDto userDto) throws Exception {
        Role userRole;
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user == null) {
            if (userDto.isAdmin()) {
                userRole = roleRepository.findByRole(UserRoles.ADMIN.name());
            } else {
                userRole = roleRepository.findByRole(UserRoles.PASSENGER.name());
            }
            user = new User();
            user.setEmail(userDto.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user.setRoles(new HashSet<>(Arrays.asList(userRole)));
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhoneNumber(userDto.getMobileNumber());


            return UserMapper.toUserDto(userRepository.save(user));
        } else {
            throw new Exception("This user is already existed.");
        }
    }







    public UserDto findUserByEmail(String email) throws Exception {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        } else {
            throw new Exception("This user is not found!");
        }
    }







    public UserDto updateProfile(UserDto userDto) throws Exception {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setFirstName(userDto.getFirstName());
            userModel.setLastName(userDto.getLastName());
            userModel.setPhoneNumber(userDto.getMobileNumber());
            return UserMapper.toUserDto(userRepository.save(userModel));
        } else {
            throw new Exception("The user is not found!");
        }
    }






    public UserDto changePassword(UserDto userDto, String newPassword) throws Exception {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setPassword(bCryptPasswordEncoder.encode(newPassword));
            return UserMapper.toUserDto(userRepository.save(userModel));
        } else {
            throw new Exception("The user is not found!");
        }
    }

}
