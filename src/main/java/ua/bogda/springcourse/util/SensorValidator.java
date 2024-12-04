package ua.bogda.springcourse.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.bogda.springcourse.dto.SensorDTO;
import ua.bogda.springcourse.services.SensorsService;

@Component
public class SensorValidator implements Validator {

    private final SensorsService sensorsService;

    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SensorDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) o;

        if (sensorsService.findByName(sensorDTO.getName()).isPresent())
            errors.rejectValue("name", "", "Sensor with this name already exists");
    }
}
