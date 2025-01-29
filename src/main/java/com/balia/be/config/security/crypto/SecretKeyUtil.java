package com.balia.be.config.security.crypto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class SecretKeyUtil {

    private final Logger logger = LoggerFactory.getLogger(SecretKeyUtil.class);

    @Value("${application.base64Key}")
    private String base64Key;
    
//    public static SecretKey getSecretKeyFromBase64(String base64Key) {
//        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
//        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
//    }

    public SecretKey getSecretKeyFromBase64() {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

}
