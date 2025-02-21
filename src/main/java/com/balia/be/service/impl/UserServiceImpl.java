package com.balia.be.service.impl;

import com.balia.be.config.security.SecurityUtils;
import com.balia.be.domain.MRole;
import com.balia.be.domain.MUser;
import com.balia.be.repository.MRoleRepository;
import com.balia.be.repository.MUserRepository;
import com.balia.be.service.MailService;
import com.balia.be.service.UserService;
import com.balia.be.service.dto.UserQuery;
import com.balia.be.service.util.RandomUtil;
import com.balia.be.web.rest.payload.request.SignupRequest;
import com.balia.be.web.rest.util.Constant;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    MailService mailService;
    
    @Autowired
    MUserRepository userRepository;
    
    @Autowired
    MRoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    
    
    @Override
    public void registerUser(SignupRequest signUpRequest) {
        MUser user = new MUser();
        user.setLogin(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail() == null ? "" : signUpRequest.getEmail());
//        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setActivated(Constant.INACTIVE);
        user.setFirstName(signUpRequest.getFirstName());
        user.setCreatedBy(Constant.SYSTEM);
        user.setCreatedDate(new Date());
        user.setActivationKey(RandomUtil.generateActivationKey());
        user.setMobileNumber(signUpRequest.getMobileNumber());

        Set<String> strRoles = signUpRequest.getRoles();
        Set<MRole> roles = new HashSet<>();

        if (!strRoles.isEmpty()) {
            strRoles.forEach(role -> {
                logger.info("Roles {}", role);
                MRole adminRole = roleRepository.findByName(role)
                        .orElseThrow(() -> new RuntimeException("Error: Role "+ role +" not found."));
                roles.add(adminRole);
            });
        }

        logger.info("Roless {}", roles);
        user.setRoles(roles);
        userRepository.save(user);

        try {
            mailService.sendCreationEmail(user);
        } catch (MessagingException | UnsupportedEncodingException e) {
            logger.error("Error MessagingException | UnsupportedEncodingException : {}", e.getMessage());
//            throw new Exception();
//            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }
        
    }

    @Override
    public boolean verify(String verificationCode, String password) {
        MUser user = userRepository.findByActivationKey(verificationCode);

        if (user == null || (Constant.ACTIVE).equals(user.getActivated())) {
            return false;
        } else {
//            user.setVerificationCode(null);
            user.setLastModifiedBy(Constant.SYSTEM);
            user.setActivated(Constant.ACTIVE);
            user.setPassword(encoder.encode(password));
            userRepository.save(user);

            return true;
        }
    }
    
    @Override
    public MUser getUserWithAuthorities() {
        Optional<MUser> user = userRepository.findOneWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin());
        MUser result = null;
        if (user.isPresent()) {
            result = user.get();
        }
        return result;
    }

    @Override
    public MUser getUserById(Long id) {
        return userRepository.findOneById(id);
    }

    @Override
    public Page<MUser> findAllByActivated(Pageable pageable, UserQuery userQuery) {
        return userRepository.findAllByActivated(pageable, userQuery.getActive());
    }

    @Override
    public boolean resendVerify(String email) {
        MUser user = userRepository.findByEmail(email);

        if (user == null || user.getActivationKey().isEmpty()) {
            return false;
        } else {
            try {
                mailService.sendCreationEmail(user);
            } catch (MessagingException | UnsupportedEncodingException e) {
                logger.error("Error resendVerify MessagingException | UnsupportedEncodingException : {}", e.getMessage());
            }

            return true;
        }
    }
}
