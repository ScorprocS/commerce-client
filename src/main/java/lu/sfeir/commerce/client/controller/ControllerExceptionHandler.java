package lu.sfeir.commerce.client.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lu.sfeir.commerce.client.clientapi.dto.ErrorResponseDto;
import lu.sfeir.commerce.client.exception.EntityNotFoundException;
import lu.sfeir.commerce.client.exception.InvalidParameterException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { EntityNotFoundException.class })
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(EntityNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ErrorResponseDto.builder().code(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).build());
	}

	@ExceptionHandler(value = { InvalidParameterException.class })
	public ResponseEntity<ErrorResponseDto> handleBadRequestException(InvalidParameterException ex) {
		return ResponseEntity.badRequest()
				.body(ErrorResponseDto.builder().code(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).build());
	}

	@ExceptionHandler( Exception.class )
	public ResponseEntity<ErrorResponseDto> handleOtherException(Exception ex) {
		return ResponseEntity.internalServerError().body(ErrorResponseDto.builder()
				.code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(ex.getMessage()).build());
	}
}
