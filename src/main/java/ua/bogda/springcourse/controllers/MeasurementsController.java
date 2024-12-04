package ua.bogda.springcourse.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ua.bogda.springcourse.dto.MeasurementDTO;
import ua.bogda.springcourse.models.Measurement;
import ua.bogda.springcourse.models.Sensor;
import ua.bogda.springcourse.services.MeasurementsService;
import ua.bogda.springcourse.services.SensorsService;
import ua.bogda.springcourse.util.MeasurementValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final MeasurementValidator measurementValidator;
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, MeasurementValidator measurementValidator, SensorsService sensorsService, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.measurementValidator = measurementValidator;
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurements() {
        List<Measurement> measurements = measurementsService.findAll();
        return measurements.stream()
                .map(measurement -> modelMapper.map(measurement, MeasurementDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public long rainingDays() {
        return measurementsService.getRainingCount();
    }


    @PostMapping("/add")
    public String addData(@RequestBody @Valid MeasurementDTO measurementDTO,
                            BindingResult bindingResult) {

        Sensor sensor = sensorsService.findByName(measurementDTO.getSensorName())
                .orElseThrow(() -> new IllegalArgumentException("Sensor with this name doesn't exist"));;

        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);
        measurement.setSensor(sensor);

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
