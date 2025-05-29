package com.leonid.user_service.service;

import com.leonid.user_service.dto.UserRequestRecord;
import com.leonid.user_service.dto.UserResponseRecord;

public interface UserService {

    UserResponseRecord createUser(UserRequestRecord userRequest);
}
