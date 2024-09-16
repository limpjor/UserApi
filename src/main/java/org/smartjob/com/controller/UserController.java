package org.smartjob.com.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.smartjob.com.model.User;
import org.smartjob.com.model.request.UserRequest;
import org.smartjob.com.model.response.ErrorResponse;
import org.smartjob.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags = "User Management")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "Register a new user", notes = "Creates a new user with the given details.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User successfully registered"),
            @ApiResponse(code = 400, message = "Bad request, invalid user data")
    })
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest user) {
        try {
            User createdUser = userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder().message(e.getMessage()));
        }
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update user information",
            description = "Updates the details of an existing user. Requires a valid user ID and updated user data."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User successfully updated"),
            @ApiResponse(code = 400, message = "Bad request, invalid user data"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<?> UpdateUser(@Valid @RequestBody UserRequest user) {
        try {
            User createdUser = userService.update(user);
            return ResponseEntity.status(HttpStatus.OK).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder().message(e.getMessage()));
        }
    }
}
