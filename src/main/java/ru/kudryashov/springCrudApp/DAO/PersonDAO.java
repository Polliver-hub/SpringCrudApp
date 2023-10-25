package ru.kudryashov.springCrudApp.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kudryashov.springCrudApp.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private static int count;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public Person getPerson(Integer id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person VALUES (?, ?, ?, ?)",
                ++count, person.getName(), person.getAge(), person.getEmail());
    }

    public void update(Integer id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), updatedPerson.getId());
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}
