package com.ey.service;

import java.util.List;

import com.ey.dto.request.UserForgetPasswordRequest;
import com.ey.dto.request.UserRequest;
import com.ey.dto.request.UserResetPasswordRequest;
import com.ey.dto.response.UserResponse;

public interface UserService {

	UserResponse createUser(UserRequest req);

	UserResponse getUserById(Long userId);
	List<UserResponse> getAllUsers();
	void forgetPassword(Long userId, UserForgetPasswordRequest req);
	void resetPassword(Long userId , UserResetPasswordRequest req);
	void logout(Long userId);

	UserResponse getByEmail();


	

	

}
