package com.example.cargologger.models;

import com.example.cargologger.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

@Component
public class CredentialsGenerator {
    private String loginId;
    private String password;
    private LoginRepository loginRepository;

    @Autowired
    public CredentialsGenerator(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    private static final String char_lowers = "abcdefghijklmnopqrstuvwxyz";
    private static final String numbers = "0123456789";

    private static final String symbols = numbers + char_lowers;

    public String generateLoginId() throws Exception {
        String result = "";
        int lowerBound = 1;
        int upperBound = 999_999;

        Set<String> uniqueValues = new HashSet();

        //values from data.sql
        uniqueValues.add("123453");
        uniqueValues.add("254533");

        int range = (upperBound - lowerBound) + 1;

        do {
            result = String.valueOf((int) (Math.random() * range) + lowerBound);
            if (!uniqueValues.contains(result)) {
                uniqueValues.add(result);
            }

            if (uniqueValues.size() == upperBound) {
                throw new Exception("Database is full!");
            }
        } while (loginRepository.existsByLoginId(result).get());

        return result;
    }

    public String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(symbols.length());
            password.append(symbols.charAt(randomIndex));
        }
        return password.toString();
    }
}
