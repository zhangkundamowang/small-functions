package com.zk.mybatisplus.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {

    /**
     * token过期时间
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    /**
     * token秘钥
     */
    private static final String TOKEN_SECRET = "secret";


    /**
     * 生成token，30分钟过期
     *
     * @param userName  用户名
     * @param loginTime 登录时间
     * @return 生成的token
     */
    public static String sign(String userName, LocalDateTime loginTime) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            System.out.println(date);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(3);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    // 设置token中需要加载的用户信息 存储自己想要留给前端的内容
                    .withClaim("userName", userName)
                    .withClaim("loginTime", loginTime.toString())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     *
     * @param token 需要校验的token
     * @return 校验是否成功
     */
    public static boolean verify(String token) {
        try {
            //设置签名的加密算法：HMAC256
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取token中信息 userName
     *
     * @param token
     * @return
     */

    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userName").asString();

        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return null;
    }
}

