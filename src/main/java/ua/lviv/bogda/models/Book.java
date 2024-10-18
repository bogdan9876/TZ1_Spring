package ua.lviv.bogda.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Name should be from 2 to 100 lenght")
    private String name;

    @Min(value = 1500, message = "Year should be 1500+")
    private int year;

    public Book() {}

    public Book(String name, int year) {
        this.name = name;
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
