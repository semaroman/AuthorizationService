package ru.netology.authorizationService.repository;

import org.springframework.stereotype.Repository;
import ru.netology.authorizationService.authorities.Authorities;
import ru.netology.authorizationService.model.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    Map<Person, List<Authorities>> personMap = new HashMap<>();

    {
        personMap.put(new Person("Alexandr", "123"), List.of(Authorities.values()));
        personMap.put(new Person("Maria", "qwerty"), List.of(Authorities.READ));
        personMap.put(new Person("Ivan", "a1b2c3d4"), List.of(Authorities.WRITE));
        personMap.put(new Person("Olga", "@$&#"), List.of(Authorities.WRITE, Authorities.DELETE));
    }

    public List<Authorities> getUserAuthorities(Person person) {
        for (Person newPerson : personMap.keySet()) {
            if (newPerson.getUser().equals(person.getUser()) &&
                    newPerson.getPassword().equals(person.getPassword())) {
                return personMap.get(newPerson);
            }
        }
        return null;
    }
}