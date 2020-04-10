package com.pjb.springbootjwt.service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pjb.springbootjwt.entity.User;
import org.springframework.stereotype.Service;
import java.util.Date;


/**
 * @author
 * @date
 */
@Service("TokenService")
public class TokenService {


    /**
     * 过期时间5秒
     */
    private static final long EXPIRE_TIME =  30 * 60 * 1000;

    public String getToken(User user) {
//        Algorithm algorithm = Algorithm.RSA256(RSAPublicKey, privateKey);
        String token="";
        token= JWT.create()
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME)) //设置过期时间).
                .withAudience(user.getId())// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }



}
