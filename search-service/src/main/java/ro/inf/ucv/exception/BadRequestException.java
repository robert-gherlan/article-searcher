package ro.inf.ucv.exception;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -8892473613986602237L;

	private final List<FieldError> fieldErrors;

	public BadRequestException(BindingResult bindingResult) {
		this.fieldErrors = bindingResult.getFieldErrors();
	}
}
