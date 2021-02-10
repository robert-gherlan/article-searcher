package ro.inf.ucv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class JWTLoginSuccessResponseDto {
	private boolean success;
	private String token;
}
