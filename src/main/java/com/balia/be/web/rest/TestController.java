package com.balia.be.web.rest;

import com.balia.be.config.security.SecurityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class TestController {

    private final Logger log = LogManager.getLogger(TestController.class);
    
    @GetMapping("/aja")
    public ResponseEntity<String> showMessage(){
        System.out.println(new Date() + "Test aja");
        return ResponseEntity.ok("test executed");
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck(){
        log.info( "Health check OK {}", new Date());
        return ResponseEntity.ok("SERVICE UP");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        log.info("Current login : {}",(SecurityUtils.getCurrentUserLogin()));
        return "User Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        log.info("Current login admin : {}",(SecurityUtils.getCurrentUserLogin()));
        return "Admin Board.";
    }
}
