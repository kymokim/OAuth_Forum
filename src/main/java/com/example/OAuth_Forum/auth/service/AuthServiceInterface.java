package com.example.OAuth_Forum.auth.service;

import com.example.OAuth_Forum.auth.dto.RequestAuth;
import com.example.OAuth_Forum.auth.dto.ResponseAuth;

import java.util.Optional;

public interface AuthServiceInterface {
    void registerUser(RequestAuth.RegisterUserDto registerUserDto);

    Optional<ResponseAuth.LoginUserRsDto> loginUser(RequestAuth.LoginUserRqDto loginUserDto);

    String createAccessToken(String userid);

    void updateUser(Optional<String> token, RequestAuth.UpdateUserDto updateUserDto);

    ResponseAuth.GetUserDto getUser(Optional<String> token);

    String oAuth(String name, String email);

    //String createRefreshToken(String userid);
    //Optional<ResponseAuth.Token> updateAccessToken(String token);
}
