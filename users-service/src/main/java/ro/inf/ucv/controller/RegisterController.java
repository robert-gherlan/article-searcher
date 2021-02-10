package ro.inf.ucv.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.dto.UserDto;
import ro.inf.ucv.exception.InvalidFieldsException;
import ro.inf.ucv.service.UserService;
import ro.inf.ucv.validator.UserValidator;

@RestController
@RequestMapping("/register")
public class RegisterController {

	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@PostMapping
	public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new InvalidFieldsException(bindingResult.getFieldErrors());
		}
		UserDto savedUser = userService.save(userDto);
		logger.debug("Register user: {}", savedUser);
		return ResponseEntity.ok(savedUser);
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.addValidators(userValidator);
	}
}