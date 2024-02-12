package by.itacademy.flatservice.contoller.utils;

import by.itacademy.exceptions.dto.UserDTO;
import by.itacademy.exceptions.enums.messages.ErrorMessages;
import by.itacademy.exceptions.exception.custom_exceptions.InternalServerException;
import by.itacademy.flatservice.config.properites.JWTProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
public class JwtTokenHandler {

    private final JWTProperty property;
    private ObjectMapper objectMapper;

    public JwtTokenHandler(JWTProperty property, ObjectMapper objectMapper) {
        this.property = property;
        this.objectMapper = objectMapper;
    }

    public UserDTO getUserInfo(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();

        try {
            return objectMapper.readValue(claims.getSubject(), UserDTO.class);
        } catch (JsonProcessingException e) {
            throw new InternalServerException(ErrorMessages.UNKNOWN_ERROR.getMessage());
        }
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(property.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(property.getSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }
}
