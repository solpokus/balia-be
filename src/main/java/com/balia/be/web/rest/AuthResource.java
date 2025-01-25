package com.balia.be.web.rest;

import com.balia.be.config.security.jwt.JwtUtils;
import com.balia.be.config.security.service.UserDetailsImpl;
import com.balia.be.domain.MUser;
import com.balia.be.repository.MUserRepository;
import com.balia.be.service.UserService;
import com.balia.be.web.rest.payload.request.LoginRequest;
import com.balia.be.web.rest.payload.request.SignupRequest;
import com.balia.be.web.rest.payload.response.JwtResponse;
import com.balia.be.web.rest.payload.response.MessageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/api/auth")
@Tag(name = "Authentication Management", description = "Operations related to authentication")
public class AuthResource {

    private final Logger logger = LoggerFactory.getLogger(AuthResource.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MUserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;
    
    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        
        MUser mUser = userService.getUserById(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getEmail(), roles, userDetails.getFirstName()));
    }

    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        logger.info("REST request to save registerUser : {}", signUpRequest);
        if (userRepository.existsByLogin(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        try {
            userService.registerUser(signUpRequest);
        } catch (Exception e) {
            logger.info("Error Exception : {}", e.getMessage());
            return ResponseEntity.internalServerError().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(String code, String password) {
        logger.info("REST request to save verifyUser : {} , {}", code , password);
        if(password == null || ("").equals(password)){
            return ResponseEntity
                    .badRequest().body(new MessageResponse("Password cannot be null!"));
        }

        if (userService.verify(code, password)) {
            return ResponseEntity
                    .ok(new MessageResponse("User verification successfully!"));
        } else {
            MUser user = userRepository.findByActivationKey(code);

            return ResponseEntity
                    .ok(new MessageResponse("Link nya sudah tidak bisa digunakan, username "+user.getLogin()+" sudah aktif dan di update passwordnya"));
        }
    }
}