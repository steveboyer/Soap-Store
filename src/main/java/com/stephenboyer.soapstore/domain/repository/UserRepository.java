package com.stephenboyer.soapstore.domain.repository;

import com.stephenboyer.soapstore.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findByLastName(String lastName);

    List<User> findByFirstName(String firstName);



}
