package ua.bogda.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bogda.springcourse.models.Sensor;
import ua.bogda.springcourse.repositories.SensorsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public List<Sensor> findAll() {
        return sensorsRepository.findAll();
    }

    public Sensor findOne(int id) {
        Optional<Sensor> foundPerson = sensorsRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }

    public Optional<Object> findByName(String name) {
        Optional<Sensor> foundSensor = sensorsRepository.findByName(name);
        return Optional.ofNullable(foundSensor.orElse(null));
    }
}
