package com.ey.service;

import com.ey.dto.request.UserRequest;
import com.ey.dto.response.UserResponse;

public interface UserService {

	UserResponse createUser(UserRequest req);

	UserResponse getUserById(Long userId);

	

	

}
