package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.UserForgetPasswordRequest;
import com.ey.dto.request.UserRequest;
import com.ey.dto.request.UserResetPasswordRequest;
import com.ey.dto.response.UserResponse;
import com.ey.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
	private  UserService userService;


	@PostMapping 
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest req) {
		return new ResponseEntity<>(userService.createUser(req), HttpStatus.CREATED);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getUserById(userId));
	}

	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@PutMapping("/forget-password/{userId}")
	public ResponseEntity<String> forgetPassword(@PathVariable Long userId,
			@Valid @RequestBody UserForgetPasswordRequest req) {
		userService.forgetPassword(userId, req);
		return ResponseEntity.ok("Password updated successfully");
	}
	@PutMapping("/reset-password/{userId}")
	public ResponseEntity<String> resetPassword(
	        @PathVariable Long userId,
	        @Valid @RequestBody UserResetPasswordRequest req) {

	    userService.resetPassword(userId, req);
	    return ResponseEntity.ok("Password reset successfully");
	}
	@PostMapping("/logout/{userId}")
	public ResponseEntity<String> logout(@PathVariable Long userId) {

	    userService.logout(userId);
	    return ResponseEntity.ok("Logout successful");
	}
	@GetMapping("/email")
    public ResponseEntity<UserResponse> getByEmail() {
        return ResponseEntity.ok(userService.getByEmail());
    }



}
