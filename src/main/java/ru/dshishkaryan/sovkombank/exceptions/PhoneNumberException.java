package ru.dshishkaryan.sovkombank.exceptions;

public class PhoneNumberException extends RuntimeException{
    public PhoneNumberException(String message) {
        super(message);
    }
}
