package ro.inf.ucv.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = -1206065822603664555L;

	public JwtAuthenticationException(String msg) {
		super(msg);
	}
}
