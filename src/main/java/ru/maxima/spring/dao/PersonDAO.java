package ru.maxima.spring.dao;

import org.springframework.stereotype.Component;
import ru.maxima.spring.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {
    private Long PEOPLE_COUNT = 7L;

    private final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";

    private Connection connection;

    public PersonDAO() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            String query = "select * from person";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Person person = new Person();

                person.setId(Long.valueOf(resultSet.getInt("id")));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }

    public Person getPersonById(Long id) {
        Person person = new Person();
        try {
            Statement statement = connection.createStatement();

            String query = "select * from person where id =" + id;

            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {

                person.setId(Long.valueOf(resultSet.getInt("id")));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                PEOPLE_COUNT++;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public void save(Person person) {
        try {
            Statement statement = connection.createStatement();

            String query = "insert into PERSON(id, name, age, email) values (" + ++PEOPLE_COUNT +
                    ", '" + person.getName() + "', " + person.getAge() + ", '" + person.getEmail() + "')";

            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void update(Long id, Person personFromForm) {
//        Person updatedPerson = getPersonById(id);
//        updatedPerson.setName(personFromForm.getName());
//        updatedPerson.setAge(personFromForm.getAge());
//        updatedPerson.setEmail(personFromForm.getEmail());
//    }

//    public void delete(Long id) {
//        people.removeIf(person -> person.getId().equals(id));
//    }
}
