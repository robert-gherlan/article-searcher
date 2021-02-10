package ro.inf.ucv.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ro.inf.ucv.dto.JWTLoginSuccessResponseDto;
import ro.inf.ucv.security.JwtTokenProvider;
import ro.inf.ucv.security.SecurityConstants;

@AllArgsConstructor
@Service
public class AuthenticationService {

	private JwtTokenProvider jwtTokenProvider;

	private AuthenticationManager authenticationManager;

	public JWTLoginSuccessResponseDto authenticate(String userName, String password) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateToken(authentication);
		String authenticationHeader = SecurityConstants.BEARER_TOKEN_PREFIX + token;

		return new JWTLoginSuccessResponseDto(true, authenticationHeader);
	}
}
