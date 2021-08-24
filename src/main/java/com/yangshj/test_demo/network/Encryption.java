package com.yangshj.test_demo.network;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class Encryption {







//    public static void rsaEncode() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
//            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//
//        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
//        KeyPair pair = keyPairGen.generateKeyPair();
//        byte[] privateKey = pair.getPrivate().getEncoded();
//        Faker faker = new Faker();
//        long start = System.currentTimeMillis();
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
//        PrivateKey key = keyFactory.generatePrivate(keySpec);
//        for (int i = 0; i < 10000; i++) {
//            byte[] randomBytes = faker.shakespeare().asYouLikcItQuote().getBytes();
//            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//            cipher.init(Cipher.ENCRYPT_MODE, key);
//            byte[] resultBytes = cipher.doFinal(randomBytes);
//            if (i % 100 == 0) {
//                System.out.format("%d/10000 done.\n", i);
//            }
//        }
//        System.out.format("time: %dms\n", System.currentTimeMillis() - start);
//    }
//
//
//    public void aesEncode() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException,
//            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
//
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(128);
//        SecretKey key = keyGenerator.generateKey();
//        Faker faker = new Faker();
//        byte[] ivBytes = new byte[16];
//        long start = System.currentTimeMillis();
//        for(int i = 0; i < 10000; i++) {
//            byte[] randomBytes = faker.shakespeare().asYouLikeItQuote().getBytes();
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            new SecureRandom().nextBytes(ivBytes);
//            IvParameterSpec iv = new IvParameterSpec(ivBytes);
//            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
//            byte[] result = cipher.doFinal(randomBytes);
//        }
//        System.out.format("time: %dms\n", System.currentTimeMillis() - start);
//    }
}
