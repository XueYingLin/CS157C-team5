package com.cs157c.subway.controller.v1.frontEndFormat;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


@Data
@Accessors(chain = true)
public class TripFormat {
    @NotBlank
    private String beginStation;

    @NotBlank
    private String endStation;

    @NotBlank
    private String trainCode;

    @Positive
    private int tripDuration;

    @Positive
    private int tripFare;
}
