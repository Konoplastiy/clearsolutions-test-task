package com.konoplastiy.clearsolutions.repository;

import com.konoplastiy.clearsolutions.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * The {@code UserRepository} interface defines methods for interacting with the {@link User} entities.
 *
 * <p>This repository provides methods to query and manage user data in the system, including CRUD operations.
 *
 * <p>It extends the JpaRepository interface to inherit basic CRUD operations for {@code User} entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByBirthDateBetween(LocalDate from, LocalDate to);
}
