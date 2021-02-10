package ro.inf.ucv.security;

import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenService {

	@Value("${jwt.secret:eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9}")
	private String secret;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private boolean isTokenNotExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.after(new Date());
	}

	public Optional<Boolean> validateToken(String token) {
		return isTokenNotExpired(token) ? Optional.of(Boolean.TRUE) : Optional.empty();
	}
}
