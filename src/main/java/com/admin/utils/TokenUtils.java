package com.weione.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.weione.common.Result;

import java.util.Calendar;
import java.util.Date;

public class TokenUtils {
    /**
     * 生成token信息
     *
     * @param //token
     * @return
     */
    public static String getToken(String userId,String userName) {
        /**
         签发对象：这个用户的id
         签发时间：现在
         有效时间：30分钟
         载荷内容：暂时设计为：这个人的名字，这个人的昵称
         加密密钥：这个人的id加上一串字符串
         */
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,30);
        Date expiresDate = nowTime.getTime();

        return JWT.create().withAudience(userId)   //签发对象
                .withIssuedAt(new Date())    //发行时间
                .withExpiresAt(expiresDate)  //有效时间
                .withClaim("userName", userName)    //载荷，随便写几个都可以
                .sign(Algorithm.HMAC256(userId+"HelloLehr"));   //加密
    }
    /**
     * 检验合法性，其中secret参数就应该传入的是用户的id
     * @param token
     * @throws Throwable
     */
    public static void verifyToken(String token, String secret) throws Throwable {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret+"HelloLehr")).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            //效验失败
            //这里抛出的异常是我自定义的一个异常，你也可以写成别的
            throw  new Throwable();
        }
    }

    /**
     * 获取签发对象
     */
    public static String getAudience(String token) throws Throwable {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            //这里是token解析失败
            throw new Throwable();
        }
        return audience;
    }


    /**
     * 通过载荷名字获取载荷的值
     */
    public static Claim getClaimByName(String token, String name){
        return JWT.decode(token).getClaim(name);
    }
}
