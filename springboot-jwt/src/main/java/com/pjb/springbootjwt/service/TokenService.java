package com.pjb.springbootjwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pjb.springbootjwt.entity.User;
import com.pjb.springbootjwt.utils.token.rsa.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @author
 * @date
 */
@Service("TokenService")
public class TokenService {
    /*
     * 创建私钥
     */
//    private templates final byte[] SECRET = "6MNSobBRCHGIO0fS6MNSobBRCHGIO0fS".getBytes();
//    private templates final byte[] SECRET = "6MNSobBRCHGIO0fS6MNSobBRCHGIO0fS".getBytes();
//    RSAKey publicKey;
//    RSAKey privateKey = new RSAKeyGenerator(2048).generate();

//    templates {
//        /**
//         * 生成公钥，公钥是提供出去，让使用者校验token的签名
//         */
//        try {
//            Keys.initKey();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 过期时间5秒
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    public String getToken(User user) {
//        Algorithm algorithm = Algorithm.RSA256(RSAPublicKey, privateKey);
//        new RSAKeyGenerator(2048).generate();
        String token = "";
        token = JWT.create()
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME)) //设置过期时间).
                .withAudience(user.getId())// 将 user id 保存到 token 里面
//                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
                .sign(Algorithm.RSA256(Keys.publicRsaKey, Keys.privateKey));// 以 ras的私钥 作为 token 的密钥
        return token;
    }


}
