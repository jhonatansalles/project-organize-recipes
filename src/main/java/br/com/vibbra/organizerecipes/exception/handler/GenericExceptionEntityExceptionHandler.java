package br.com.vibbra.organizerecipes.exception.handler;

import br.com.vibbra.organizerecipes.exception.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GenericExceptionEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<ErrorDTO> handleConflict(Exception ex, WebRequest request) {
		return handleException(ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	protected ResponseEntity<ErrorDTO> handleException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (ex instanceof BadCredentialsException) {
			status = HttpStatus.UNAUTHORIZED;
		}
		if (HttpStatus.BAD_REQUEST.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, RequestAttributes.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(new ErrorDTO(ex) , headers, status);
	}
}
