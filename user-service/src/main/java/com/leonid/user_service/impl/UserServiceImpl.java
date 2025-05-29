package com.leonid.user_service.impl;

import com.leonid.user_service.dto.UserRequestRecord;
import com.leonid.user_service.dto.UserResponseRecord;
import com.leonid.user_service.exception.EmailAlreadyExistsException;
import com.leonid.user_service.model.User;
import com.leonid.user_service.repository.UserRepository;
import com.leonid.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    @Transactional
    public UserResponseRecord createUser(UserRequestRecord userRequest) {
        Optional<User> user = userRepository.findUserByEmail(userRequest.email());
        if (user.isPresent()){
            throw new EmailAlreadyExistsException(" Email Already Exist for Employee name " + userRequest.email());
        }
        User newUser = new User();
        newUser.setName(userRequest.name());
        newUser.setEmail(userRequest.email());
        newUser.setCreatedAt(LocalDateTime.now());

        User saved = userRepository.save(newUser);
        return new UserResponseRecord(saved.getId(), saved.getName(), saved.getEmail());
    }
}
