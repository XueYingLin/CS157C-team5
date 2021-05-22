package com.cs157c.subway.controller.v1.frontEndFormat;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Accessors(chain = true)
public class ProfileFormat {
    @NotBlank
    @Size(min = 1, max = 40)
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Size(min = 5, max = 13)
    private String phoneNumber;
}
