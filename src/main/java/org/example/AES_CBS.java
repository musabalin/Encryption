package org.example;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author musa.balin
 */
public class AES_CBS {

    private static final String key = "MSDBw1MElZRMdkJCS1veRtCA";
    private static final String initVector = "OliN2elaK9kbGCMs";
    private static final String CRYPT_TYPE_DES = "AES";
    private static final String CRYPT_TYPE_DES_CIPHER = "AES/CBC/PKCS5Padding";

    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), CRYPT_TYPE_DES);

            Cipher cipher = Cipher.getInstance(CRYPT_TYPE_DES_CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), CRYPT_TYPE_DES);

            Cipher cipher = Cipher.getInstance(CRYPT_TYPE_DES_CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
