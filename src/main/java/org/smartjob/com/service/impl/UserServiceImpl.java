package org.smartjob.com.service.impl;

import org.smartjob.com.constant.Constants;
import org.smartjob.com.model.Phone;
import org.smartjob.com.model.User;
import org.smartjob.com.model.request.UserRequest;
import org.smartjob.com.repository.UserRepository;
import org.smartjob.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(UserRequest userRequest) throws Exception {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new Exception(Constants.EMAIL_EXIST);
        }

        User user = User.builder()
                .id(UUID.randomUUID().getLeastSignificantBits())
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token(UUID.randomUUID().toString())
                .isActive(true)
                .phones(new ArrayList<>())
                .build();

        user.setPhones(userRequest.getPhones()
                .stream()
                .map( phone -> {
                    return Phone
                            .builder()
                            .number(phone.getNumber())
                            .citycode(phone.getCitycode())
                            .countrycode(phone.getCountrycode())
                            .user(user)
                            .build();
                })
                .collect(Collectors.toList()));

        return userRepository.save(user);

    }

    @Override
    public User update(UserRequest userRequest) throws Exception {

        User user = User.builder()
                .id(userRequest.getId())
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .modified(LocalDateTime.now())
                .token(UUID.randomUUID().toString())
                .isActive(true)
                .phones(new ArrayList<>())
                .build();

        user.setPhones(userRequest.getPhones()
                .stream()
                .map( phone -> {
                    return Phone
                            .builder()
                            .id(phone.getId())
                            .number(phone.getNumber())
                            .citycode(phone.getCitycode())
                            .countrycode(phone.getCountrycode())
                            .user(user)
                            .build();
                })
                .collect(Collectors.toList()));

        return userRepository.save(user);

    }

}



