package ua.bogda.springcourse.dto;


import javax.validation.constraints.*;


public class MeasurementDTO {
    @NotNull(message = "Value shouldn't be empty")
    @Min(value = -100, message = "Value should be bigger")
    @Max(value = 100, message = "Value should be lower")
    private int value;

    @NotNull(message = "Raining status wrong")
    private boolean raining;

    @NotNull(message = "Sensor shouldn't be null")
    private String sensorName;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
}
