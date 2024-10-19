package ua.lviv.bogda.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.lviv.bogda.models.City;
import ua.lviv.bogda.models.Person;
import java.util.List;


@Component
public class CitiesDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CitiesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<City> index() {
        return jdbcTemplate.query("SELECT * FROM City", new BeanPropertyRowMapper<>(City.class));
    }

    public City show(int id) {
        return jdbcTemplate.query("SELECT * FROM City WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(City.class))
                .stream().findAny().orElse(null);
    }

    public void save(City city) {
        jdbcTemplate.update("INSERT INTO City(name) VALUES(?)", city.getName());
    }

    public void update(int id, City updatedCity) {
        jdbcTemplate.update("UPDATE City SET name=? WHERE id=?", updatedCity.getName(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM City WHERE id=?", id);
    }

    public List<Person> getCitizens(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE city_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class));
    }
}
