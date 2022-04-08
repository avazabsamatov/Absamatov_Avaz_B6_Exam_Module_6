package uz.pdp.absamatov_avaz_b6_exam_1_variant.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;
@Component
public class JWTProvider {
    long expirationTime = 1000 * 30;
    String secretKey = "secret";

    public String generatedToken(String username) {
        String jwtToken = Jwts.
                builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return jwtToken;
    }

    public String refreshToken(String username) {
        String refreshToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return refreshToken;
    }

    public Boolean validateToken(String clientToken, HttpServletRequest request) {


        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(clientToken);
            return true;
        } catch (ExpiredJwtException e) {
            request.setAttribute("expired", e.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String clientToken) {
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(clientToken)
                .getBody()
                .getSubject();
    }

}
