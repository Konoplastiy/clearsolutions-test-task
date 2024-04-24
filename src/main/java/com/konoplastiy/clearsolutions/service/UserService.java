package com.konoplastiy.clearsolutions.service;

import com.konoplastiy.clearsolutions.common.exception.ResourceNotFoundException;
import com.konoplastiy.clearsolutions.common.exception.UserNotFoundException;
import com.konoplastiy.clearsolutions.common.mapper.UserMapper;
import com.konoplastiy.clearsolutions.entity.User;
import com.konoplastiy.clearsolutions.payload.user.UserDto;
import com.konoplastiy.clearsolutions.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${user.min.age}")
    private int minAge;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public User createUser(@NonNull final User user) {
        LocalDate minAgeDate = LocalDate.now().minusYears(minAge);
        if (user.getBirthDate().isAfter(minAgeDate)) {
            throw new IllegalArgumentException("User must be at least " + minAge + " years old.");
        }
        return userRepository.save(user);
    }

    @Transactional
    public void updateUser(@NonNull final Long id, @NonNull final UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        if (userDto.getBirthDate() != null) {
            LocalDate minAgeDate = LocalDate.now().minusYears(minAge);
            if (userDto.getBirthDate().isAfter(minAgeDate)) {
                throw new IllegalArgumentException("User must be at least " + minAge + " years old.");
            }
        }
        userMapper.updateUser(user, userDto);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(@NonNull final Long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<User> searchUsersByBirthDateRange(@NotNull final LocalDate fromDate, @NotNull final LocalDate toDate) {
        if (fromDate.isAfter(toDate)) {
            throw new IllegalArgumentException("'From' date must be less than 'To' date.");
        }
        return userRepository.findUsersByBirthDateBetween(fromDate, toDate);
    }
}
