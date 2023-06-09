package com.example.OAuth_Forum.auth.security;

public interface AuthToken<T> {
    boolean validate();
    T getClaims();
}
