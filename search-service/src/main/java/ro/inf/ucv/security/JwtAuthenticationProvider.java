package ro.inf.ucv.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import ro.inf.ucv.exception.JwtAuthenticationException;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private final JwtTokenService jwtService;

	public JwtAuthenticationProvider(JwtTokenService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) {
		try {
			String token = (String) authentication.getCredentials();
			String username = jwtService.getUsernameFromToken(token);
			return jwtService.validateToken(token).map(a -> new JwtAuthenticatedProfile(username, true))
					.orElseThrow(() -> new JwtAuthenticationException("JWT Token validation failed"));
		} catch (JwtException ex) {
			throw new JwtAuthenticationException("Failed to verify token");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.equals(authentication);
	}
}
