package org.example.expert.config;

import org.example.expert.domain.auth.exception.AuthException;
import org.example.expert.domain.auth.exception.DuplicateEmailException;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.common.exception.ServerException;
import org.example.expert.domain.manager.exception.InvalidManagerAssignmentException;
import org.example.expert.domain.manager.exception.SelfAssignmentException;
import org.example.expert.domain.manager.exception.UnauthorizedTodoAccessException;
import org.example.expert.domain.todo.exception.NotFoundTodoException;
import org.example.expert.domain.user.exception.IncorrectPasswordException;
import org.example.expert.domain.user.exception.NotFoundUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Map<String, Object>> invalidRequestExceptionException(InvalidRequestException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return getErrorResponse(status, ex.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Map<String, Object>> handleAuthException(AuthException ex) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return getErrorResponse(status, ex.getMessage());
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<Map<String, Object>> handleServerException(ServerException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return getErrorResponse(status, ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleNotFoundUserException(NotFoundUserException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return getErrorResponse(status, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleNIncorrectPasswordException(IncorrectPasswordException e) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return getErrorResponse(status, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleNotFoundTodoException(NotFoundTodoException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return getErrorResponse(status, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleUnauthorizedTodoAccessException(UnauthorizedTodoAccessException e) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        return getErrorResponse(status, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleUSelfAssignmentException(SelfAssignmentException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return getErrorResponse(status, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleInvalidManagerAssignmentException(InvalidManagerAssignmentException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return getErrorResponse(status, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleDuplicateEmailException(DuplicateEmailException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        return getErrorResponse(status, e.getMessage());
    }

    public ResponseEntity<Map<String, Object>> getErrorResponse(HttpStatus status, String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", status.name());
        errorResponse.put("code", status.value());
        errorResponse.put("message", message);

        return new ResponseEntity<>(errorResponse, status);
    }
}

