package lu.sfeir.commerce.client.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lu.sfeir.commerce.client.clientapi.dto.ErrorResponseDto;
import lu.sfeir.commerce.client.exception.EntityNotFoundException;
import lu.sfeir.commerce.client.exception.InvalidParameterException;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseDto handleResourceNotFoundException(EntityNotFoundException ex) {
        return ErrorResponseDto.builder()
                .code(404)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = {InvalidParameterException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto handleBadRequestException(InvalidParameterException ex) {
        return ErrorResponseDto.builder()
                .code(400)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseDto handleOtherException(Exception ex) {
        return ErrorResponseDto.builder()
                .code(500).message(ex.getMessage())
                .build();
    }
}
