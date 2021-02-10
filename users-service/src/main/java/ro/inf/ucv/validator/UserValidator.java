package ro.inf.ucv.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.AllArgsConstructor;
import ro.inf.ucv.dto.UserDto;
import ro.inf.ucv.service.UserService;

@AllArgsConstructor
@Component
public class UserValidator implements Validator {

	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDto userDto = (UserDto) target;
		if (userDto.getPassword() != null && !Objects.equals(userDto.getPassword(), userDto.getConfirmPassword())) {
			errors.rejectValue("password", "user.password.not.match", "Passwords don't match.");
		}

		if (userDto.getConfirmPassword() != null
				&& !Objects.equals(userDto.getPassword(), userDto.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "user.confirm.password.not.match", "Passwords don't match.");
		}

		if (userService.existsByEmail(userDto.getEmail())) {
			errors.rejectValue("email", "user.email.already.registered", "Email already registered.");
		}

		if (userService.existsByUsername(userDto.getUsername())) {
			errors.rejectValue("username", "user.username.already.registered", "Username already registered.");
		}
	}
}
