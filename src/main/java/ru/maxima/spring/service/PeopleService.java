package ru.maxima.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.spring.models.Person;
import ru.maxima.spring.repositories.PeopleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(Long id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Long id, Person personFromForm) {
        personFromForm.setId(id);
        peopleRepository.save(personFromForm);
    }

    @Transactional
    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }

//    List<Person> findAllByName(String name);
//    List<Person> findAllOrderByEmail(String email);
//    Person findByNameStartingWith(String start);
//    List<Person> findByNameOrEmail(String name, String email);

    public List<Person> findAllByName(String name) {
        return peopleRepository.findAllByName(name);
    }

    public List<Person> findAllOrderByEmail(String email) {
        return peopleRepository.findAllOrderByEmail(email);
    }

    public List<Person> findByNameOrEmail(String name, String email) {
        return peopleRepository.findByNameOrEmail(name, email);
    }

    public List<Person> findByNameStartingWith(String start) {
        return peopleRepository.findByNameStartingWith(start);
    }

    public List<Person> findByNameStartingWithOrderByNameDesc(String start) {
        return peopleRepository.findByNameStartingWithOrderByNameDesc(start);
    }


}
