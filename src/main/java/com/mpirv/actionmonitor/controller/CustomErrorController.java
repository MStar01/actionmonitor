package com.mpirv.actionmonitor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ControllerAdvice
@Slf4j
public class CustomErrorController implements ErrorController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllException(final Exception ex) {
        log.error(ex.getMessage() + " - "+ ex.getStackTrace());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Ups!");
    }

    @RequestMapping("/error")
    @ResponseBody
    public String error() {
        return "<h2>Ups!</h2>";
    }
}
