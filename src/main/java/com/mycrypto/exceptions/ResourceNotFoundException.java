package com.mycrypto.exceptions;


public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s:%s", resourceName,fieldName,fieldValue));
    }
}
