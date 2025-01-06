package org.example.expert.domain.todo.exception;

public class NotFoundTodoException extends RuntimeException{

    public NotFoundTodoException(String message) {
        super(message);
    }
}
