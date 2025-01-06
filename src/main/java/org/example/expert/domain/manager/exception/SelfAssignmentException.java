package org.example.expert.domain.manager.exception;

public class SelfAssignmentException extends RuntimeException{

    public SelfAssignmentException(String message) {
        super(message);
    }
}
