package ro.inf.ucv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4102664770399335935L;

	public RecordNotFoundException(String message) {
		super(message);
	}

}
