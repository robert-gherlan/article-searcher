package ro.inf.ucv.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidFieldsException extends RuntimeException {

	private static final long serialVersionUID = -7930448938870399614L;

	private final List<FieldError> fieldErrors;

	public InvalidFieldsException(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

}
