package com.kennenalphateam.genshin.error;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Hidden
public class ErrorController  extends AbstractErrorController {
    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    private final String ERROR_PATH = "/error";


    @RequestMapping(ERROR_PATH)
    public ResponseEntity<ErrorResponse> handleError(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return ErrorResponse.toResponseEntity(status);
    }
}
