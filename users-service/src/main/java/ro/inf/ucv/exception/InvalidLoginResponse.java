package ro.inf.ucv.exception;

import lombok.Data;

@Data
public class InvalidLoginResponse {
	private final String username = "Invalid Username";
	private final String password = "Invalid Password";

}
