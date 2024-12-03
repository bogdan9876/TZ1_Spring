package ua.bogda.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.bogda.springcourse.models.Sensor;
import ua.bogda.springcourse.services.SensorsService;
import ua.bogda.springcourse.util.SensorValidator;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorsService sensorsService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorsController(SensorsService sensorsService, SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping()
    public List<Sensor> getSensors() {
        return sensorsService.findAll();
    }

    @PostMapping("/registration")
    public String addSensor(@RequestBody @Valid Sensor sensor,
                            BindingResult bindingResult) {

        sensorValidator.validate(sensor, bindingResult);

        if (bindingResult.hasErrors())
            return bindingResult.getAllErrors().get(0).getDefaultMessage();

        sensorsService.save(sensor);
        return "Sensor has been added successfully";
    }

    @GetMapping("/{id}")
    public Sensor getSensor(@PathVariable("id") int id) {
        return sensorsService.findOne(id);
    }
}
