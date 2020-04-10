package com.demo.service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.demo.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getId())
                .sign(Algorithm.HMAC256(user.getPassword()));

        return token;
    }

    public User findUserById(String userId){
        if (userId.equals("1")){
            User user = new User("1","zhangsan","12345");
            return user;
        }else {
            return null;
        }
    }

}
