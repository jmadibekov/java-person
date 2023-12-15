package com.example.person.person;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PersonConfig {
    @Bean
    CommandLineRunner commandLineRunner(PersonRepository repository) {
        return args -> {
            Person nurba = new Person(
                    "nurba",
                    "nurba@gmail.com",
                    LocalDate.of(2002, Month.APRIL, 5)
            );

            Person ali = new Person(
                    "ali",
                    "ali@gmail.com",
                    LocalDate.of(2005, Month.APRIL, 5)
            );

            repository.saveAll(List.of(ali, nurba));
        };
    }

}
