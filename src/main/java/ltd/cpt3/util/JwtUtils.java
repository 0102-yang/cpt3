package ltd.cpt3.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.UUID;

/**
 * @author yang
 * @date Oct 06 2021 2:46 PM
 */
public class JwtUtils {

    private static final byte[] SECRET = "FEF1114F2D1A5BF56F5D8F442CACE951A500462C337A4F3992A9767128BF31A5".getBytes();

    private static final String ISS = "yang-0FBC";

    public static String createToken(String subject, Map<String, Object> claims, long timeout, TimeUnit timeUnit) {
        String jti = UUID.randomUUID().toString();
        long now = System.currentTimeMillis();
        long expiredTime = TimeUnit.MILLISECONDS.convert(timeout, timeUnit);
        Date iat = new Date();
        Date exp = new Date(now + expiredTime);

        JwtBuilder builder = Jwts.builder().setClaims(claims).setId(jti).setIssuer(JwtUtils.ISS).setIssuedAt(iat).setNotBefore(iat).setExpiration(exp).setSubject(subject).signWith(SignatureAlgorithm.HS256, JwtUtils.SECRET);
        return builder.compact();
    }

}
