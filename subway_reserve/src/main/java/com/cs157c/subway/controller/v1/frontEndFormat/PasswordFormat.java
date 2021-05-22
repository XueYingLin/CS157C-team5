package com.cs157c.subway.controller.v1.frontEndFormat;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Accessors(chain = true)
public class PasswordFormat {
    @NotBlank
    @Size(min = 5, max = 12)
    private String password;

    private String email;
}
