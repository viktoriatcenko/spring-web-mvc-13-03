package ru.maxima.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.spring.models.Person;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByName(String name);
    List<Person> findAllOrderByEmail(String email);
    List<Person> findByNameStartingWith(String start);
    List<Person> findByNameStartingWithOrderByNameDesc(String start);
    List<Person> findByNameOrEmail(String name, String email);
}
