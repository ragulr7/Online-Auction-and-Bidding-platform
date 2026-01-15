package com.ey.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ey.dto.request.UserRequest;
import com.ey.dto.response.UserResponse;
import com.ey.entity.User;
import com.ey.mapper.UserMapper;
import com.ey.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	public UserServiceImpl(UserRepository userRepository , UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}
	
	
	@Override
	public UserResponse createUser(UserRequest req) {
		
		User user = userMapper.toEntity(req);
		User savedUser = userRepository.save(user);
		return userMapper.toResponse(savedUser);
	}

	@Override
	public UserResponse getUserById(Long userId) {
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	    return userMapper.toResponse(user);
	}
	
	

}
