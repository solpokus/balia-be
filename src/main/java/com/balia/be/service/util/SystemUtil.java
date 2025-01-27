package com.balia.be.service.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SystemUtil {

    @Value("${spring.profiles.active}")
    private String myProfile;

    @Value("${application.baseUrl}")
    private String appBaseUrl;

    @Autowired
    private HttpServletRequest request;

    public String getBaseUrl(){
        String baseUrl = "";
        if(myProfile.equals("local")){
            // Get the current request URL
            String requestURL = request.getRequestURL().toString();

            // Extract the base URL
            baseUrl = requestURL.substring(0, requestURL.length() - request.getRequestURI().length());
        } else {
            baseUrl = appBaseUrl;
        }
        return baseUrl;
    }
    
    public String getDate(String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date());
    }
}
