package ru.maxima.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.spring.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> getAllPeople() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPersonById(Long id) {
        return jdbcTemplate.query("select * from person where id = ?", new Object[]{id} , new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(name, age, email) VALUES (?,?,?)",
                person.getName(), person.getAge(), person.getEmail());
    }

    public void update(Long id, Person personFromForm) {
        jdbcTemplate.update("update person set name = ?, age = ?, email = ? where id = ?",
                personFromForm.getName(), personFromForm.getAge(), personFromForm.getEmail(), id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("delete from person where id = ?", id );
    }
}
