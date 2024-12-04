package ua.bogda.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.bogda.springcourse.models.Measurement;
import ua.bogda.springcourse.services.MeasurementsService;
import ua.bogda.springcourse.util.MeasurementValidator;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, MeasurementValidator measurementValidator) {
        this.measurementsService = measurementsService;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping()
    public List<Measurement> getSensors() {
        return measurementsService.findAll();
    }

    @GetMapping("/rainyDaysCount")
    public long rainingDays() {
        return measurementsService.getRainingCount();
    }


    @PostMapping("/add")
    public String addData(@RequestBody @Valid Measurement measurement,
                            BindingResult bindingResult) {

        measurementValidator.validate(measurement, bindingResult);

        if (bindingResult.hasErrors())
            return bindingResult.getAllErrors().get(0).getDefaultMessage();

        measurementsService.save(measurement);
        return "Measurement has been added successfully";
    }

    @GetMapping("/{id}")
    public Measurement getMeasurement(@PathVariable("id") int id) {
        return measurementsService.findOne(id);
    }
}
