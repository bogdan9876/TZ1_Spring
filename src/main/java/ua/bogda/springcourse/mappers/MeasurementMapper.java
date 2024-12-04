package ua.bogda.springcourse.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.bogda.springcourse.dto.MeasurementDTO;
import ua.bogda.springcourse.models.Measurement;

@Component
public class MeasurementMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MeasurementDTO toDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    public Measurement toModel(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}