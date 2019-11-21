package com.auth.authorization.repository;

import com.auth.authorization.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findById(int userId);

}
