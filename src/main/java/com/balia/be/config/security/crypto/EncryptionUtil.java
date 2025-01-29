package com.balia.be.config.security.crypto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

@Service
public class EncryptionUtil {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String CHARSET = "UTF-8";

    @Value("${application.base64Iv}")
    private String base64Iv;

//    // Generate an IV (Initialization Vector)
//    public IvParameterSpec generateIv() {
//        byte[] iv = new byte[16]; // 16 bytes for AES
//        new java.security.SecureRandom().nextBytes(iv);
//        return new IvParameterSpec(iv);
//    }`

    // Generate an IV (Initialization Vector)
    // Convert a Base64-encoded IV string to IvParameterSpec
    public IvParameterSpec generateIv() {
        byte[] ivBytes = Base64.getDecoder().decode(base64Iv);
        return new IvParameterSpec(ivBytes);
    }

    // Encrypt Method
    public String encrypt(String plainText, SecretKey secretKey, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] cipherText = cipher.doFinal(plainText.getBytes(CHARSET));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    // Decrypt Method
    public String decrypt(String encryptedText, SecretKey secretKey, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(plainText, CHARSET);
    }
}
