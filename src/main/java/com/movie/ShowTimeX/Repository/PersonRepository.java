package com.movie.ShowTimeX.Repository;

import com.movie.ShowTimeX.Model.Admin;
import com.movie.ShowTimeX.Model.Person;
import com.movie.ShowTimeX.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
}


