package com.company.project.exception;

public class DuplicateTenantException extends RuntimeException {

    public DuplicateTenantException(String message) {
        super(message);
    }
}
