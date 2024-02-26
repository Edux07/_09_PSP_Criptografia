package com.example;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

import static javax.crypto.Cipher.SECRET_KEY;

public class EncryptionUtil {
    private static final String SECRET_KEY = "My_key_1234";
    public static String encriptar(String frase) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            KeySpec keySpec = new DESKeySpec(SECRET_KEY.getBytes());
            SecretKey key = keyFactory.generateSecret(keySpec);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytesEncriptados = cipher.doFinal(frase.getBytes());
            return Base64.getEncoder().encodeToString(bytesEncriptados);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String desencriptar(String fraseEncriptada) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            KeySpec keySpec = new DESKeySpec(SECRET_KEY.getBytes());
            SecretKey key = keyFactory.generateSecret(keySpec);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] bytesDesencriptados = cipher.doFinal(Base64.getDecoder().decode(fraseEncriptada));
            return new String(bytesDesencriptados);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
