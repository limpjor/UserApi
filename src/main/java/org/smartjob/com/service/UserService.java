package org.smartjob.com.service;

import org.smartjob.com.model.User;
import org.smartjob.com.model.request.UserRequest;

public interface UserService {

    User register(UserRequest userRequest) throws Exception;

    User update(UserRequest userRequest) throws Exception;
}
