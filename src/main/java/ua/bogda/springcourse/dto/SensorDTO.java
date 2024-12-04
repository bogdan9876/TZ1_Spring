package ua.bogda.springcourse.dto;

import javax.validation.constraints.*;

public class SensorDTO {
    @NotEmpty(message = "Sensor name shouldn't be empty")
    @Size(min = 3, max = 30, message = "Sensor name should be between 3 and 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
