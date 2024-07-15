package com.haianh123.library.controller;

import com.haianh123.library.dto.request.RefreshTokenRequest;
import com.haianh123.library.dto.request.SignInRequest;
import com.haianh123.library.dto.request.SignUpRequest;
import com.haianh123.library.dto.response.ApiResponse;
import com.haianh123.library.dto.response.TokenResponse;
import com.haianh123.library.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ApiResponse<TokenResponse> login(@Valid @RequestBody SignInRequest request){
        return ApiResponse.<TokenResponse>builder()
                .result(authenticationService.authenticate(request))
                .build();
    }

    @PostMapping("/refresh")
    public ApiResponse<TokenResponse> refresh(@Valid @RequestBody RefreshTokenRequest refreshToken){

        return ApiResponse.<TokenResponse>builder()
                .result(authenticationService.refreshToken(refreshToken.getRefreshToken()))
                .build();
    }

    @PostMapping("/signup")
    public ApiResponse register(@Valid @RequestBody SignUpRequest request){
        authenticationService.signUp(request);
        return ApiResponse.builder()
                .message("Đăng ký thành công!")
                .build();
    }

    @GetMapping("/verify")
    public ApiResponse verifyEmail(@Valid @RequestParam(value = "email") String email,@Valid @RequestParam(value = "code") String code){
        authenticationService.verifyEmail(code, email);
        return ApiResponse.builder()
                .message("Đăng ký thành công!")
                .build();
    }

    @GetMapping("/hello")
    public ApiResponse<String> hello(){
        log.info("Hello");
        return ApiResponse.<String>builder()
                .result("Hello World!")
                .build();
    }
}






