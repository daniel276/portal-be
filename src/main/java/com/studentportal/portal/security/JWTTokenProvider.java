package com.studentportal.portal.security;

import com.studentportal.portal.domain.UserLogin;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.studentportal.portal.security.SecurityConstants.EXPIRATION_TIME;
import static com.studentportal.portal.security.SecurityConstants.SECRET;

@Component
public class JWTTokenProvider {

    //generate the token
    public String generateToken(Authentication authentication){
        UserLogin user = (UserLogin) authentication.getPrincipal();

        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        String userId = Long.toString(user.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId()))); //these are the information that we want to include in our token
        claims.put("username", user.getUsername()); //when we decode the token, we can get the information from the client
//        claims.put("fullName", user.getFullName());
        claims.put("role", user.getRole());

        return Jwts.builder()//this is how we build the token
                .setSubject(userId)
                .setClaims(claims)//claims is information about the user
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    //validate the token
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            System.out.println("Invalid JWT Signature");
        }catch (MalformedJwtException ex){
            System.out.println("Invalid JWT Token");
        }catch (ExpiredJwtException ex){
            System.out.println("Expired JWT Token");
        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupported JWT Token");
        }catch (IllegalArgumentException ex){
            System.out.println("JWT Claims string is empty");
        }
        return false;
    }

    //get user id from the token
    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();

        String id = (String)claims.get("id");

        return Long.parseLong(id);
    }

}
