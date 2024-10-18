package ua.lviv.bogda.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Name should be from 2 to 100 lenght")
    private String name;

    @Min(value = 1900, message = "Year should be 1900+")
    private int year_of_birth;

    public Person() {

    }

    public Person(String name, int year_of_birth) {
        this.name = name;
        this.year_of_birth = year_of_birth;
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

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }
}
