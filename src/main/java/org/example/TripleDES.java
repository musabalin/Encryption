package org.example;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author musa.balin
 */
public class TripleDES {
    private static final String key = "111111111111111111111111";
    private static final String vector = "77777777";
    private static final String CRYPT_TYPE_DES = "TripleDES";
    private static final String CRYPT_TYPE_DES_CIPHER = "TripleDES/CBC/PKCS5Padding";

    public TripleDES() {
        super();
    }

    private byte[] crypt(byte[] str, int type) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), CRYPT_TYPE_DES);
            final byte[] iv = vector.getBytes();
            Cipher cipher = Cipher.getInstance(CRYPT_TYPE_DES_CIPHER);
            cipher.init(type, skeySpec, new IvParameterSpec(iv));
            return cipher.doFinal(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encrypt(String text) {
        return (text != null) ? Base64.encodeBase64String(this.crypt(text.getBytes(), Cipher.ENCRYPT_MODE)) : null;
    }

    public String decrypt(String text) {
        return (text != null) ? new String(this.crypt(Base64.decodeBase64(text), Cipher.DECRYPT_MODE)) : null;
    }
}
