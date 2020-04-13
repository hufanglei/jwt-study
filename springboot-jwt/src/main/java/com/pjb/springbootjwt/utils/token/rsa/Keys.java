package com.pjb.springbootjwt.utils.token.rsa;

import java.security.Key;

import java.security.KeyPair;

import java.security.KeyPairGenerator;

import java.security.interfaces.RSAPrivateKey;

import java.security.interfaces.RSAPublicKey;

import java.util.HashMap;

import java.util.Map;

import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.PostConstruct;

/**
 * java生成rsa的公钥和私钥
 * https://jingyan.baidu.com/article/6dad5075f33466a123e36ecb.html
 */
@Component
@SuppressWarnings("unused")
public class Keys {

    public static final String KEY_ALGORITHM = "RSA";
    //    public templates final String SIGNATURE_ALGORITHM = "MD5withRSA";
    public static final String PUBLIC_KEY = "RSAPublicKey";
    public static final String PRIVATE_KEY = "RSAPrivateKey";

    public static RSAPrivateKey privateKey;
    public static RSAPublicKey publicRsaKey;



    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        byte[] publicKey = key.getEncoded();
        return encryptBASE64(key.getEncoded());
    }

    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        byte[] privateKey = key.getEncoded();
        return encryptBASE64(key.getEncoded());

    }


    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }


    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    @PostConstruct
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        publicRsaKey = (RSAPublicKey) keyPair.getPublic();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicRsaKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static void main(String[] args) {
        Map<String, Object> keyMap;
        try {
            keyMap = initKey();
            String publicKey = getPublicKey(keyMap);
            System.out.println("打印公钥:====");
            System.out.println(publicKey);
            String privateKey = getPrivateKey(keyMap);
            System.out.println("打印私钥:====");
            System.out.println(privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}