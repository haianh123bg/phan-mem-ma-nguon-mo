package com.haianh123.library.service;

import com.haianh123.library.dto.request.SignInRequest;
import com.haianh123.library.dto.response.TokenResponse;
import com.haianh123.library.exception.AppException;
import com.haianh123.library.exception.ErrorCode;
import com.haianh123.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthemticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public TokenResponse authenticate(SignInRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException ex){
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED));

        String accessToken  = jwtService.generateToken(user);
        String refreshToken = jwtService.gennerateRefreshToken(user);
        List<String> roles = Collections.singletonList(user.getRoles().toString());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .roles(roles)
                .userId(user.getId())
                .build();
    }
}








