package com.stephenboyer.soapstore.domain.repository;

import com.stephenboyer.soapstore.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);

    List<User> findByLastName(String lastName);

    List<User> findByFirstName(String firstName);



}
