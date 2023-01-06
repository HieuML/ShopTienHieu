package com.example.shoptienhieu.exception;

import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.response.BaseRes;
import com.example.shoptienhieu.dto.response.BaseUpdatedRes;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);


    @ExceptionHandler(ResourceNotFoundException.class)
    public BaseRes resourceNotFoundException(ResourceNotFoundException ex) {
        logger.error(ex.getMessage());
        return new BaseRes<>(HttpStatus.NOT_FOUND.value(), ex.getMessage());

    }

    @ExceptionHandler(UpdateInvoiceOrProductNotFoundIdException.class)
    public BaseRes updateNotFoundIdException(UpdateInvoiceOrProductNotFoundIdException ex) {
        logger.error(ex.getMessage());
        return new BaseRes<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new BaseUpdatedRes(ex.getId(), false));

    }
    @ExceptionHandler(IllegalStateException.class)
    public BaseRes IllegalStateExceptionException(IllegalStateException ex) {
        logger.error(ex.getMessage());
        return new BaseRes<>(HttpStatus.NOT_FOUND.value(), ex.getMessage());

    }

    @ExceptionHandler(TokenRefreshException.class)
    public BaseRes handleTokenRefreshException(TokenRefreshException ex) {
        logger.error(ex.getMessage());
        return new BaseRes(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(JwtException.class)
    public BaseRes handleJwtException() {
        logger.error(TextStatus.INVALID_ACCESS_TOKEN);
        return new BaseRes(
                HttpStatus.FORBIDDEN.value(), TextStatus.INVALID_ACCESS_TOKEN
        );
    }


}
