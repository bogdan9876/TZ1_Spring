package ua.bogda.springcourse.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bogda.springcourse.dto.MeasurementDTO;
import ua.bogda.springcourse.mappers.MeasurementMapper;
import ua.bogda.springcourse.models.Measurement;
import ua.bogda.springcourse.repositories.MeasurementsRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    public Measurement findOne(int id) {
        Optional<Measurement> foundData = measurementsRepository.findById(id);
        return foundData.orElse(null);
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setCreatedAt(new Date());
        measurementsRepository.save(measurement);
    }

    public long getRainingCount() {
        return measurementsRepository.countByRaining(true);
    }
}
