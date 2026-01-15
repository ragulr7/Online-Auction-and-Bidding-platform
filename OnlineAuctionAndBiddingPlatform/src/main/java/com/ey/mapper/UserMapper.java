package com.ey.mapper;

import org.springframework.stereotype.Component;

import com.ey.dto.request.UserRequest;
import com.ey.dto.response.UserResponse;
import com.ey.entity.User;

@Component
public class UserMapper {
	
	public User toEntity(UserRequest req) {
		User user = new User();
		user.setName(req.getName());
		user.setEmail(req.getEmail());
		user.setPassword(req.getPassword());
		user.setRole(req.getRole());
		user.setStatus("ACTIVE");
		return user;
	}
	
	public UserResponse toResponse(User user) {
		UserResponse res = new UserResponse();
		res.setUserId(user.getUserId());
		res.setName(user.getName());
		res.setEmail(user.getEmail());
		res.setRole(user.getRole());
		res.setStatus(user.getStatus());
		return res;
	}

}
