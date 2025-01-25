package com.balia.be.service;

import com.balia.be.domain.MUser;
import com.balia.be.service.dto.UserQuery;
import com.balia.be.web.rest.payload.request.SignupRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for managing users.
 */
@Service
public interface UserService {
    
    void registerUser(SignupRequest signupRequest);
    
    boolean verify(String verificationCode, String password);
    
    MUser getUserWithAuthorities();
    
    MUser getUserById(Long id);
    
    Page<MUser> findAllByActivated(Pageable pageable, UserQuery userQuery);
}
