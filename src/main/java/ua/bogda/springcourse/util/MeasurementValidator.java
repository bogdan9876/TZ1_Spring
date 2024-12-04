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

        String sensorName = measurement.getSensor().getName();

        Optional<Sensor> existingSensor = sensorsService.findByName(sensorName);
        if (existingSensor.isEmpty()) {
            errors.rejectValue("sensor", "", "Sensor with this name doesn't exist");
        } else {
            measurement.setSensor(existingSensor.get());
        }
    }
}
