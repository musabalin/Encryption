package org.example;

/**
 * @author musa.balin
 */

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AES_ECB {

    public AES_ECB() {
        super();
    }

    private static SecretKeySpec secretKey;
    private static final String vector = "111111111111111111111111";
    private static final String CRYPT_TYPE_DES = "AES";
    private static final String CRYPT_TYPE_DES_CIPHER = "AES/ECB/PKCS5Padding";

    public void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            byte[] key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, CRYPT_TYPE_DES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String strToEncrypt) {
        try {
            setKey(vector);
            Cipher cipher = Cipher.getInstance(CRYPT_TYPE_DES_CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return java.util.Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public String decrypt(String strToDecrypt) {
        try {
            setKey(vector);
            Cipher cipher = Cipher.getInstance(CRYPT_TYPE_DES_CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder()
                    .decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}

