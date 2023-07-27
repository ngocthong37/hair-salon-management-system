package com.hairsalon.security.jwt;

import com.hairsalon.service.UserDetailsImp;
import org.slf4j.Logger;

import java.security.SignatureException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
    @Component
    public class JWTUtils {
        private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);

        private String jwtSecret = "HRlELXqpSB";

        private int jwtExpirationMs = 604800000; //(ms) => /1000 /60 /60 /24 => days

        public String generateJwtToken(UserDetailsImp userDetails) {
            return Jwts.builder()
                    .setSubject((userDetails.getUsername()))
                    .setIssuedAt(new Date())
                    .setExpiration(generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
        }

        public Date generateExpirationDate() {
            return new Date(System.currentTimeMillis() + jwtExpirationMs);
        }

        public Claims getClaimsFromJwtToken(String token) {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        }

        private boolean isTokenExpired(Claims claims) {
            return claims.getExpiration().after(new Date());
        }

        public String getUserNameFromJwtToken(String token) {
            Claims claims = getClaimsFromJwtToken(token);
            if (claims != null && isTokenExpired(claims)) {
                return claims.getSubject();
            }
            return null;
        }

        public boolean validateJwtToken(String authToken) throws SignatureException {
            try {
                Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
                return true;
            } catch (MalformedJwtException e) {
                logger.error("Invalid JWT token: {}", e.getMessage());
            } catch (ExpiredJwtException e) {
                logger.error("JWT token is expired: {}", e.getMessage());
            } catch (UnsupportedJwtException e) {
                logger.error("JWT token is unsupported: {}", e.getMessage());
            } catch (IllegalArgumentException e) {
                logger.error("JWT claims string is empty: {}", e.getMessage());
            }
            return false;
        }
}
