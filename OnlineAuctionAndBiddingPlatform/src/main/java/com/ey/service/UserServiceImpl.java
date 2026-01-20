package com.ey.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ey.dto.request.UserForgetPasswordRequest;
import com.ey.dto.request.UserRequest;
import com.ey.dto.request.UserResetPasswordRequest;
import com.ey.dto.response.UserResponse;
import com.ey.entity.User;
import com.ey.enums.Role;
import com.ey.exception.EmailNotFoundException;
import com.ey.exception.UserAlreadyExistException;
import com.ey.exception.UserNotFoundException;
import com.ey.exception.WrongPasswordException;
import com.ey.mapper.UserMapper;
import com.ey.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private  UserMapper userMapper;
	
	Logger log = LoggerFactory.getLogger(UserService.class);
	
	
	
	@Override
	public UserResponse createUser(UserRequest req) {
		if(req.getRole() == Role.ADMIN) {
			if (userRepository.findByRole(Role.ADMIN).isPresent()) {
				log.error("Admin already exists");
	            throw new UserAlreadyExistException("Admin already exists");
	        }
		}
		
		User user = userMapper.toEntity(req);
		User savedUser = userRepository.save(user);
		return userMapper.toResponse(savedUser);
	}

	@Override
	public UserResponse getUserById(Long userId) {
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> {
	            	log.error("User not found");
	            	return new UserNotFoundException("User not found");});
	    return userMapper.toResponse(user);
	}
	@Override
	public List<UserResponse> getAllUsers() {
	    return userRepository.findAll()
	            .stream()
	            .map(userMapper::toResponse)
	            .toList();
	}

	@Override
	public void forgetPassword(Long userId, UserForgetPasswordRequest req) {
		    User user = userRepository.findById(userId)
		            .orElseThrow(() ->{
		            	log.error("User not found");
		           return new UserNotFoundException("User not found");});

		    if (!user.getEmail().equals(req.getEmail())) {
		    	log.error("Email does not match");
		        throw new EmailNotFoundException("Email does not match");
		    }

		    user.setPassword(req.getNewPassword());
		    userRepository.save(user);
		}

	@Override
	public void resetPassword(Long userId, UserResetPasswordRequest req) {
		User user = userRepository.findById(userId)
				.orElseThrow(() ->{
					log.error("User not found");
				return new UserNotFoundException("User nto found");});
		if(!user.getPassword().equals(req.getOldPassword())) {
			log.error("Old password is wrong");
			throw new WrongPasswordException("Old pasword is wrong");
		}
		user.setPassword(req.getNewPassword());
		userRepository.save(user);
		
	}
	@Override
	public void logout(Long userId) {
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> {
	            	log.error("User not found");
	           return new UserNotFoundException("User not found");});
	    if ("INACTIVE".equals(user.getStatus())) {
	    	log.error("User already logged out");
	        throw new UserNotFoundException("User already logged out");
	    }
	    user.setStatus("INACTIVE");
	    userRepository.save(user);
	}
	@Override
	public UserResponse getByEmail() {
	    String email = SecurityContextHolder
	            .getContext()
	            .getAuthentication()
	            .getName();

	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() ->
	                    new RuntimeException("User not found"));

	    return userMapper.toResponse(user);
	}



		
	}

	
	

