package me.erano.com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    public static String hash(String password) {
        try {
            // MessageDigest nesnesini SHA-256 algoritmasıyla oluşturun
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Şifreyi byte dizisine dönüştürün
            byte[] passwordBytes = password.getBytes();

            // Byte dizisini hashleyin
            byte[] hashBytes = md.digest(passwordBytes);

            // Hash'i hexadecimal formata dönüştürün
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            // Hash'i döndürün
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

}
