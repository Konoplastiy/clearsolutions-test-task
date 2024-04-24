package com.konoplastiy.clearsolutions.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * This class represents a user entity and defines the schema for creating a table in the database.
 * It establishes a connection between this class and the corresponding table in the database using ORM.
 *
 * <p>Users are individuals who interact with the system, providing personal information
 * and participating in various activities such as creating content, joining groups, and
 * engaging in discussions.
 *
 * <p>This class is mapped to the "users" database table and contains attributes representing
 * user information such as email, name, birth date, address, and phone number.
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userInfo")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String address;

    private String phoneNumber;
}
