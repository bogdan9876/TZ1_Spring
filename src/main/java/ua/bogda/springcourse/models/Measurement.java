package ua.bogda.springcourse.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "measurement")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Value shouldn't be empty")
    @Min(value = -100, message = "Value should be bigger")
    @Max(value = 100, message = "Value should be lower")
    @Column(name = "value")
    private int value;

    @NotNull(message = "Raining status wrong")
    @Column(name = "raining")
    private boolean raining;

    @ManyToOne
    @NotNull(message = "Sensor shouldn't be null")
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Measurement() {}

    public Measurement(int value, boolean raining) {
        this.value = value;
        this.raining = raining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
