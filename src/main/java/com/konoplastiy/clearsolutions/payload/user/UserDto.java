package com.konoplastiy.clearsolutions.payload.user;

import com.konoplastiy.clearsolutions.common.validator.email.ValidEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * {@code UserDto} is a DTO (Data Transfer Object) representation used to transfer
 * user information between the client and the server.
 *
 * <p>It contains fields such as email, firstName, lastName, birthDate, address, and phoneNumber,
 * representing various attributes of a user. These fields are used for creating, updating,
 * and transferring user data.
 *
 * @see ValidEmail
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "Email must not be blank")
    @ValidEmail
    @Schema(description = "Email of the user", example = "user@example.com")
    private String email;

    @NotBlank(message = "First name must not be blank")
    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;

    @NotNull(message = "Birth date must not be null")
    @Schema(description = "Date of birth of the user", example = "1990-01-01")
    private LocalDate birthDate;

    @NotBlank(message = "Address must not be blank")
    @Schema(description = "Address of the user", example = "123 Pushkin St, City, Country")
    private String address;

    @NotBlank(message = "Phone number must not be blank")
    @Schema(description = "Phone number of the user", example = "+3809674372325")
    private String phoneNumber;
}
