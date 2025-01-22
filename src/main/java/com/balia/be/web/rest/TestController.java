package com.balia.be.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/aja")
    public ResponseEntity<String> showMessage(){
        System.out.println(new Date() + "Test aja");
        return ResponseEntity.ok("test executed");
    }
}
