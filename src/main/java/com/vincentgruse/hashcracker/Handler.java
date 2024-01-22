package com.vincentgruse.hashcracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Handler {

    // Check if the input is a valid MD5 hash
    public static boolean isValidInput(String input) {
        int length = input.length();
        String pattern = "^[0-9a-z]+$";
        return length == 32 && input.matches(pattern);
    }

    // Check if the input is a valid file with a .txt extension
    public static boolean isValidFile(String input) {
        File file = new File(input);
        return input.endsWith(".txt") && file.isFile();
    }

    // Calculate the MD5 hash for a given input string
    public static String calcMDFive(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Read inputs from a file, calculates MD5 hashes, and returns string if found
    public static String convertToMDFive(String filePath, String userHash) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String input;
            while ((input = br.readLine()) != null) {
                String md5Hash = calcMDFive(input);
                if (Objects.equals(md5Hash, userHash)) {
                    return input;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

