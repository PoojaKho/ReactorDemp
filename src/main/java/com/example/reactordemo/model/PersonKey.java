package com.example.reactordemo.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.data.cassandra.core.cql.Ordering.DESCENDING;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;


@PrimaryKeyClass
public class PersonKey {

        @PrimaryKeyColumn(name = "first_name", type = PARTITIONED)
        private String firstName;

        public LocalDateTime getDateOfBirth() {
                return dateOfBirth;
        }

        public void setDateOfBirth(LocalDateTime dateOfBirth) {
                this.dateOfBirth = dateOfBirth;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
        }


        @PrimaryKeyColumn(name = "date_of_birth", ordinal = 0)
        private LocalDateTime dateOfBirth;


        @PrimaryKeyColumn(name = "person_id", ordinal = 1, ordering = DESCENDING)
        private UUID id;


        public PersonKey(String firstName, LocalDateTime dateOfBirth, UUID id) {
                this.firstName = firstName;
                this.dateOfBirth = dateOfBirth;
                this.id = id;
        }
}
