package com.springboot.subway.DTO.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;

@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainDto {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getTrainMake() {
        return trainMake;
    }

    public void setTrainMake(String trainMake) {
        this.trainMake = trainMake;
    }



    private String code;

    private int capacity;

    private String trainMake;

}
