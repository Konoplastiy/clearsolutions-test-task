package com.konoplastiy.clearsolutions.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.konoplastiy.clearsolutions.common.mapper.UserMapper;
import com.konoplastiy.clearsolutions.entity.User;
import com.konoplastiy.clearsolutions.payload.user.UserDto;
import com.konoplastiy.clearsolutions.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@FieldDefaults(level = AccessLevel.PRIVATE)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    UserMapper userMapper;

    final ObjectMapper objectMapper = new ObjectMapper();

    final User USER = User.builder()
            .id(1L)
            .email("user@example.com")
            .firstName("John")
            .lastName("Doe")
            .birthDate(LocalDate.of(1990, 1, 1))
            .address("123 Pushkin St, City, Country")
            .phoneNumber("+3809674372325")
            .build();

    final UserDto USER_DTO = UserDto.builder()
            .email("user@example.com")
            .firstName("John")
            .lastName("Doe")
            .birthDate(LocalDate.of(1990, 1, 1))
            .address("123 Pushkin St, City, Country")
            .phoneNumber("+3809674372325")
            .build();

    @BeforeAll
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void createUserShouldReturnStatusOkAndValidData() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(USER);
        when(userMapper.toDto(any(User.class))).thenReturn(USER_DTO);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("user@example.com"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.birthDate").value("1990-01-01"))
                .andExpect(jsonPath("$.address").value("123 Pushkin St, City, Country"))
                .andExpect(jsonPath("$.phoneNumber").value("+3809674372325"));
    }

    @Test
    void updateUserShouldReturnStatusNoContent() throws Exception {
        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_DTO)))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteUserByIdShouldReturnStatusNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void searchUsersByBirthDateRangeShouldReturnStatusOkAndValidData() throws Exception {
        List<User> userList = Arrays.asList(USER);
        when(userService.searchUsersByBirthDateRange(any(LocalDate.class), any(LocalDate.class))).thenReturn(userList);
        when(userMapper.toListDto(anyList())).thenReturn(Collections.singletonList(USER_DTO));

        mockMvc.perform(get("/api/v1/users")
                        .param("fromDate", "1990-01-01")
                        .param("toDate", "1990-01-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("user@example.com"))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[0].birthDate").value("1990-01-01"))
                .andExpect(jsonPath("$[0].address").value("123 Pushkin St, City, Country"))
                .andExpect(jsonPath("$[0].phoneNumber").value("+3809674372325"));
    }
}
