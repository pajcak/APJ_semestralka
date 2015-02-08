package org.lib.utils;

public class LibraryException extends Exception {

    public LibraryException() {
    }

    public LibraryException(Exception cause) {
        super(cause);
    }

    public LibraryException(String msg) {
        super(msg);
    }
}
