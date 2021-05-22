package com.cs157c.subway.dto.mapper;

import com.cs157c.subway.dto.model.user.RoleDto;
import com.cs157c.subway.dto.model.user.UserDto;
import com.cs157c.subway.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;


@Component
public class UserMapper {

    public static UserDto toUserDto(User user) {

        UserDto udo = new UserDto();


        udo.setEmail(user.getEmail());
        udo.setFirstName(user.getFirstName());
        udo.setLastName(user.getLastName());
        udo.setMobileNumber(user.getPhoneNumber());
        udo.setRoles(
                new HashSet<RoleDto>(
                        user.getRoles()
                                .stream()
                                .map(role -> new ModelMapper().map(role, RoleDto.class))
                                .collect(Collectors.toSet())
                )
        );


        return udo;
    }

}
