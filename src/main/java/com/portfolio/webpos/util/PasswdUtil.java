package com.portfolio.webpos.util;


import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Component
public class PasswdUtil {

    public String makeSalt(){
        Random random = new Random();
        byte [] salt = new byte[16];
        random.nextBytes(salt);
        return byteToString(salt);
    }

    public String makePassword(String password, String salt){
        String temp = password + salt;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(temp.getBytes());
            byte[] digest = md.digest();
            return byteToString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String byteToString(byte[]bytes){
        StringBuilder sb = new StringBuilder();
        for(byte a: bytes){
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }
}
