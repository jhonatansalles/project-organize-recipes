package br.com.vibbra.organizerecipes.exception.handler;

import br.com.vibbra.organizerecipes.exception.ValidationException;
import br.com.vibbra.organizerecipes.exception.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { ValidationException.class })
	protected @ResponseBody ResponseEntity<ErrorDTO> handleConflict(ValidationException ex, WebRequest request) {
		return handleException(ex, ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private ResponseEntity<ErrorDTO> handleException(Exception ex, ValidationException body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (HttpStatus.BAD_REQUEST.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, RequestAttributes.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(new ErrorDTO(body), headers, status);
	}
}
