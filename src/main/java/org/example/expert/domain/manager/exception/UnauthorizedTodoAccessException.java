package org.example.expert.domain.manager.exception;

public class UnauthorizedTodoAccessException extends RuntimeException{

    public UnauthorizedTodoAccessException(String message) {
        super(message);
    }
}
