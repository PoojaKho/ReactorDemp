package com.example.reactordemo.repository;

import com.example.reactordemo.model.Person;
import com.example.reactordemo.model.PersonKey;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface PersonRepository extends ReactiveCassandraRepository<Person, PersonKey> {
    Flux<Person> findByKeyFirstName(final String firstName);
    Flux<Person> findByKeyFirstNameAndKeyDateOfBirthGreaterThan(
            final String firstName, final LocalDateTime dateOfBirth);

}