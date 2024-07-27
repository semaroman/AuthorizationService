package ru.netology.authorizationService.service;

import org.springframework.stereotype.Service;
import ru.netology.authorizationService.authorities.Authorities;
import ru.netology.authorizationService.exception.InvalidCredentials;
import ru.netology.authorizationService.exception.UnauthorizedUser;
import ru.netology.authorizationService.model.Person;
import ru.netology.authorizationService.repository.UserRepository;

import java.util.List;

@Service
public class AuthorizationService {
    UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(Person person) {
        if (isEmpty(person.getUser()) || isEmpty(person.getPassword())) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(person);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + person.getUser());
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}