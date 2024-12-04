package ua.bogda.springcourse.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.bogda.springcourse.dto.SensorDTO;
import ua.bogda.springcourse.models.Sensor;

@Component
public class SensorMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public SensorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SensorDTO toDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    public Sensor toModel(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}