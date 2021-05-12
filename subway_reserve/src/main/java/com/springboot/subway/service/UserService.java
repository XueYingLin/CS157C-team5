package com.springboot.subway.service;

import com.springboot.subway.DTO.dtoMapper.UserMapper;
import com.springboot.subway.DTO.entry.UserDto;
import com.springboot.subway.model.user.User;
import com.springboot.subway.model.user.UserRoles;
import com.springboot.subway.model.user.role;
import com.springboot.subway.repository.RoleRepository;
import com.springboot.subway.repository.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    private ModelMapper modelMapper;

    public UserDto register(UserDto userDto) throws Exception {
        role userRole;
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user == null) {
            if (userDto.isAdmin()) {
                userRole = roleRepository.findByRole(UserRoles.ADMIN.name());
            } else {
                userRole = roleRepository.findByRole(UserRoles.TRAVELLER.name());
            }


            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setPassword(userDto.getPassword());
            user.setRoles(new ArrayList<>(Arrays.asList(userRole)));
            return UserMapper.mapToUserDto(userRepository.save(user));
        } else {
            throw new Exception("Can not signUp this account.");
        }
    }


    public User findUserByEmaill(String email) throws Exception {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            return userRepository.findByEmail(email);
        } else {
            throw new Exception("This user is not found!");
        }

    }

    public void saveUser(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new ArrayList<role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
}
