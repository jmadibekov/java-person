package com.example.person.person;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public void addNewPerson(Person person) {
        Optional<Person> personByEmail = personRepository.findPersonByEmail(person.getEmail());
        if (personByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        personRepository.save(person);
    }

    public void deletePerson(Long id) {
        boolean exists = personRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("person with id " + id + " does not exists");
        }
        personRepository.deleteById(id);
    }
    @Transactional
    public void updatePerson(Long personId, String name, String email) {
        Person person = personRepository.findById(personId).orElseThrow(
                () -> new IllegalStateException("person with id " + personId + " does not exists")
        );

        if (name != null && !name.isEmpty() && !Objects.equals(person.getName(), name)) {
            person.setName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(person.getEmail(), email)) {
            Optional<Person> personOptional = personRepository.findPersonByEmail(email);
            if (personOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            person.setEmail(email);
        }
    }
}
