package org.example;

/**
 * @author musa.balin
 */
public class Main {
    public static void main(String[] args) throws Exception {

        AES_ECB eu = new AES_ECB();
        String a = eu.encrypt("MusaBalın");
        System.out.println("Encrypt: " + a);
        String b = eu.decrypt(a);
        System.out.println("Decrypt: " + b);

        System.out.println("*****************************");

        TripleDES tripleDES = new TripleDES();

        String originalString = "MusaBalın";
        String e = tripleDES.encrypt(originalString);
        System.out.println(e);
        String d = tripleDES.decrypt(e);
        System.out.println(d);


        System.out.println("****************************");
        String clean = "MusaBalın";
        AES_CBS aesCbs = new AES_CBS();
        String encrypted = aesCbs.encrypt(clean);
        String decrypted = aesCbs.decrypt(encrypted);

        System.out.println(encrypted);
        System.out.println(decrypted);

    }


}