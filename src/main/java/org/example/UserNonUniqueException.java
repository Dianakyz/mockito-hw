package org.example;

public class UserNonUniqueException extends Throwable {
    public UserNonUniqueException(String message) {
        super(message);
    }
}
