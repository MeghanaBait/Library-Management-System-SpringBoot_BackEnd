package com.example.Library.Management.system.Exceptions;

public class MaximumBookIssueLimitReachedException extends Exception{
    public MaximumBookIssueLimitReachedException(String message) {
        super(message);
    }
}
