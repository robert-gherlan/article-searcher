package ro.inf.ucv.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;

public class ErrorFieldMapperUtils {

	private ErrorFieldMapperUtils() {
	}

	public static final Map<String, String> mapFieldErrors(BindingResult bindingResult) {
		Map<String, String> errorMap = new HashMap<>();
		bindingResult.getFieldErrors().forEach(e -> errorMap.put(e.getField(), e.getDefaultMessage()));

		return errorMap;
	}
}
