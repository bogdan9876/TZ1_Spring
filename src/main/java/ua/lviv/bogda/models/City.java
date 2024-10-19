package ua.lviv.bogda.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class City {
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Name should be from 2 to 100 lenght")
    private String name;

    public City() {}

    public City(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
