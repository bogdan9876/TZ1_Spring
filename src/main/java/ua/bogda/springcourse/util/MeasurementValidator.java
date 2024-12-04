package ua.bogda.springcourse.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.bogda.springcourse.models.Measurement;
import ua.bogda.springcourse.models.Sensor;
import ua.bogda.springcourse.services.SensorsService;

import java.util.Optional;

@Component
public class MeasurementValidator implements Validator {

    private final SensorsService sensorsService;

    public MeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurement.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurement measurement = (Measurement) o;

        Optional<Object> existingSensor = sensorsService.findByName(measurement.getSensor().getName());
        if (existingSensor.isEmpty()) {
            errors.rejectValue("sensor", "", "Sensor with this name don't exists");
        } else {
            measurement.setSensor((Sensor) existingSensor.get());
        }
    }
}
