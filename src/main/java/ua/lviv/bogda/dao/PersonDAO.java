package ua.lviv.bogda.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.lviv.bogda.models.Book;
import ua.lviv.bogda.models.City;
import ua.lviv.bogda.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, year_of_birth) VALUES(?, ?)", person.getName(), person.getYear_of_birth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, year_of_birth=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getYear_of_birth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public Optional<Person> getPersonByName(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<City> getLocation(int id) {
        return jdbcTemplate.query("SELECT City.* FROM Person JOIN City ON Person.city_id = City.id " +
                        "WHERE Person.id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(City.class)).stream().findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Person SET city_id=NULL WHERE id=?", id);

    }

    public void assign(int id, City selectedCity) {
        jdbcTemplate.update("UPDATE Person SET city_id=? WHERE id=?", selectedCity.getId(), id);
    }
}
