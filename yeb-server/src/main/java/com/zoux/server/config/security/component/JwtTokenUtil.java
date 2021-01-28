package com.zoux.server.config.security.component;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken 工具类
 */
@Component
public class JwtTokenUtil {
    //荷载头名字
    private static final String CLAIM_KEY_USERNAME = "sub";
    //荷载头创建时间
    private static final String CLAIM_KEY_CREATE = "created";
    //通过配置目录拿参数,通过value注解
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    //1 根据用户信息生成token

    /**
     * 根据用户信息生成token
     *
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        //用户信息从security里面的UserDetails 获取

        //先准备荷载
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATE, new Date());

        //创建token

        return generateToken(claims);
    }
    //重写generateToken（）

    /**
     * 根据荷载生成JWT TOKEN
     *
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                //准备荷载
                .setClaims(claims)
                //失效时间
                .setExpiration(generateExpirationDate())
                //签名
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成token 失效时间
     *
     * @return
     */
    private Date generateExpirationDate() {
        //系统当前秒数+ 配置失效时间
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


    //2 生成token后，可以从token拿用户名

    /**
     * 从token中获取登陆用户名
     *
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {


        String username;
        //根据tokrn获取荷载
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 从token获取荷载
     *
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    //3 判断token是否失效

    /**
     * 验证token是否有效
     *
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        //token 是否过期
        //token荷载里面的用户名和userDetails里面的用户名是否一致
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }

    /**
     * 判断token是否失效
     *
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        //判断date是否在当前时间之前
        return expiredDate.before(new Date());
    }

    /**
     * 从token获取失效时间
     *
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    //4 判断token 是否可以被刷新

    /**
     * 判断token是否可以被刷新
     *
     * @param token
     * @return
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    //5 刷新token

    /**
     * 刷新token
     *
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATE, new Date());
        return generateToken(claims);
    }

}
