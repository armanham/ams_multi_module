package com.bdg.exception;

public class WrongIdException extends RuntimeException{

    public WrongIdException(Long id) {
        super("Passed non-positive number as parameter 'id': " + id);
    }
}
