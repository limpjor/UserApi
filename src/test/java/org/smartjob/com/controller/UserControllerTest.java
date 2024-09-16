package org.smartjob.com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.smartjob.com.constant.Constants;
import org.smartjob.com.model.User;
import org.smartjob.com.model.request.UserRequest;
import org.smartjob.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegisterUserSuccess() throws Exception {
        User createdUser = User.builder()
                .id(1L)
                .name(Constants.NAME)
                .email(Constants.EMAIL)
                .password(Constants.PASSWORD)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(true)
                .token(Constants.TOKEN)
                .phones(new ArrayList<>())
                .build();

        when(userService.register(any(UserRequest.class)))
                .thenReturn(createdUser);

        UserRequest userRequest = new UserRequest();
        userRequest.setName(Constants.NAME);
        userRequest.setEmail(Constants.EMAIL);
        userRequest.setPassword(Constants.PASSWORD2);

        String userRequestJson = objectMapper.writeValueAsString(userRequest);

        mockMvc.perform(post(Constants.URL_REGISTER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRequestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(Constants.NAME))
                .andExpect(jsonPath("$.email").value(Constants.EMAIL))
                .andExpect(jsonPath("$.token").value(Constants.TOKEN));
    }

}
