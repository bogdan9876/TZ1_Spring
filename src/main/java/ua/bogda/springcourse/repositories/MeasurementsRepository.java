package ua.bogda.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.bogda.springcourse.models.Measurement;


@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    long countByRaining(boolean raining);
}
