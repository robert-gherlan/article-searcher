package ro.inf.ucv.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequestDto {

	@NotBlank(message = "Username cannot be blank")
	private String username;

	@NotBlank(message = "Password cannot be blank")
	private String password;
}
