package ro.inf.ucv.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ro.inf.ucv.dto.JWTLoginSuccessResponseDto;
import ro.inf.ucv.dto.LoginRequestDto;
import ro.inf.ucv.service.AuthenticationService;
import ro.inf.ucv.utils.ErrorFieldMapperUtils;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

	private AuthenticationService authenticationService;

	@PostMapping
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequest, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(ErrorFieldMapperUtils.mapFieldErrors(bindingResult), HttpStatus.BAD_REQUEST);
		}

		JWTLoginSuccessResponseDto jwtLoginSuccessResponseDto = authenticationService
				.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

		return ResponseEntity.ok(jwtLoginSuccessResponseDto);
	}
}
