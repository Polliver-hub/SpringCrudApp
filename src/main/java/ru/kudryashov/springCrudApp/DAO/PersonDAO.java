package ru.kudryashov.springCrudApp.DAO;

import org.springframework.stereotype.Component;
import ru.kudryashov.springCrudApp.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class PersonDAO {
    private List<Person> people;
    private static int COUNT_PEOPLE;

    {
        people = new ArrayList<>();
        people.add(new Person(++COUNT_PEOPLE,"Ivan", 15, "ivan@gmail.com"));
        people.add(new Person(++COUNT_PEOPLE,"Petr",25, "Petr@yandex.com"));
        people.add(new Person(++COUNT_PEOPLE,"Vasiliy",103, "Vasiliy@inbox.com"));
        people.add(new Person(++COUNT_PEOPLE,"Aleksey",12, "Aleksey@mail.com"));
    }

    public List<Person> getAllPeople() {
        return people;
    }

    public Person getPerson(Integer id) {
        return people.stream().filter(person -> Objects.equals(person.getId(), id)).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++COUNT_PEOPLE);
        people.add(person);
    }

    public void update(Integer id, Person updatedPerson) {
        Person personToBeUpdated = getPerson(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(Integer id) {
        people.removeIf(person -> Objects.equals(person.getId(), id));
    }

}
