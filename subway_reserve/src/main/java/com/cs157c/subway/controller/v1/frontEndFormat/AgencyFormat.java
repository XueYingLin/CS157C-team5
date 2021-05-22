package com.cs157c.subway.controller.v1.frontEndFormat;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Accessors(chain = true)
public class AgencyFormat {
    @NotBlank
    @Size(min = 5, max = 100)
    private String agencyName;

    @NotBlank
    @Size(max = 100)
    private String agencyDetails;

    @NotBlank
    @Size(min = 8, max = 100)
    private String contactInfo;

    @NotBlank
    @Size(min = 8, max = 100)
    private String address;

    @NotBlank
    @Size(min = 5, max = 100)
    private String officeHour;
}
