package com.konoplastiy.clearsolutions.service;

import com.konoplastiy.clearsolutions.common.exception.UserNotFoundException;
import com.konoplastiy.clearsolutions.common.mapper.UserMapper;
import com.konoplastiy.clearsolutions.entity.User;
import com.konoplastiy.clearsolutions.payload.user.UserDto;
import com.konoplastiy.clearsolutions.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.konoplastiy.clearsolutions.common.ApplicationConstants.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class UserServiceTest {

    @Value("${user.min.age}")
    int minAge;

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    final UserDto USER_DTO = UserDto.builder()
            .email(USER_TEST_EMAIL)
            .firstName(USER_TEST_FIRSTNAME)
            .lastName(USER_TEST_LASTNAME)
            .birthDate(LocalDate.of(1990, 1, 1))
            .address(USER_TEST_ADDRESS)
            .phoneNumber(USER_TEST_PHONE)
            .build();

    final User USER = User.builder()
            .id(1L)
            .email(USER_TEST_EMAIL)
            .firstName(USER_TEST_FIRSTNAME)
            .lastName(USER_TEST_LASTNAME)
            .birthDate(LocalDate.of(1990, 1, 1))
            .address(USER_TEST_ADDRESS)
            .phoneNumber(USER_TEST_PHONE)
            .build();

    @Test
    void createUser_ValidUser_ShouldReturnCreatedUser() {
        when(userRepository.save(any(User.class))).thenReturn(USER);

        User createdUser = userService.createUser(USER);

        assertEquals(USER, createdUser);
        verify(userRepository, times(1)).save(USER);
    }

    @Test
    void createUser_UserUnderMinimumAge_ShouldThrowIllegalArgumentException() {
        LocalDate minAgeDate = LocalDate.now().minusYears(minAge);
        User userUnderMinAge = User.builder()
                .birthDate(minAgeDate.plusDays(1))
                .build();

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(userUnderMinAge));

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void updateUser_ValidUser_ShouldUpdateAndSaveUser() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(USER));
        when(userRepository.save(any(User.class))).thenReturn(USER);

        userService.updateUser(userId, USER_DTO);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(USER);
    }

    @Test
    void updateUser_UserNotFound_ShouldThrowUserNotFoundException() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userId, USER_DTO));

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void deleteUserById_UserExists_ShouldDeleteUser() {
        Long userId = 1L;

        userService.deleteUserById(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void searchUsersByBirthDateRange_ValidDates_ShouldReturnListOfUsers() {
        LocalDate fromDate = LocalDate.of(1990, 1, 1);
        LocalDate toDate = LocalDate.of(2000, 12, 31);
        when(userRepository.findUsersByBirthDateBetween(fromDate, toDate)).thenReturn(Collections.singletonList(USER));

        List<User> users = userService.searchUsersByBirthDateRange(fromDate, toDate);

        assertEquals(Collections.singletonList(USER), users);
        verify(userRepository, times(1)).findUsersByBirthDateBetween(fromDate, toDate);
    }

    @Test
    void searchUsersByBirthDateRange_FromDateAfterToDate_ShouldThrowIllegalArgumentException() {
        LocalDate fromDate = LocalDate.of(2000, 12, 31);
        LocalDate toDate = LocalDate.of(1990, 1, 1);

        assertThrows(IllegalArgumentException.class, () -> userService.searchUsersByBirthDateRange(fromDate, toDate));

        verify(userRepository, never()).findUsersByBirthDateBetween(fromDate, toDate);
    }
}