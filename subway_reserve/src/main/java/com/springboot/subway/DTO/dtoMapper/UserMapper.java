package com.springboot.subway.DTO.dtoMapper;

import com.springboot.subway.DTO.entry.RoleDto;
import com.springboot.subway.DTO.entry.UserDto;
import com.springboot.subway.model.user.User;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPassword());
        userDto.setRoles(new ArrayList<RoleDto>(
                        user.getRoles()
                                .stream()
                                .map(role -> new ModelMapper().map(role, RoleDto.class))
                                .collect(Collectors.toList())
        ));

        return userDto;
    }
}









