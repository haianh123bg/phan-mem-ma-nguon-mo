package com.haianh123.library.service;

import com.haianh123.library.dto.request.SignInRequest;
import com.haianh123.library.dto.request.SignUpRequest;
import com.haianh123.library.dto.response.TokenResponse;
import com.haianh123.library.entity.Role;
import com.haianh123.library.entity.User;
import com.haianh123.library.exception.AppException;
import com.haianh123.library.exception.ErrorCode;
import com.haianh123.library.repository.RoleRepository;
import com.haianh123.library.repository.UserRepository;
import com.haianh123.library.utils.RandomCodeGenerator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final MyEmailService myEmailService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

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
        log.info(user.getRoles().toString());
        List<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .roles(roles)
                .userId(user.getId())
                .build();
    }

    public void signUp(SignUpRequest request) {

        // Kiểm tra xem tài khoản có tk trong db hay chưa
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        // Sinh ngẫu nhiên mã xác thực
        String code = String.valueOf(RandomCodeGenerator.generateVerifyCode());

        // Viết form code html cho email
        String content = myEmailService.generateVerificationEmailContent(code);

        // Gửi Email
        myEmailService.sendHtmlEmail(request.getEmail(), "Account authentication code", content);

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setVerifyCode(code);
        user.setIsVerify(false);
        user.setIsActive(true);
        user.setVerifyNumberOfTimes(5);

        Role role = roleRepository.findById(1L).get();
        user.setRoles(List.of(role));

        // Lưu mã xác thực vào database
        userRepository.save(user);

    }

    public void verifyEmail(String code, String email){

        // Kiểm tra email có tồn tại hay không
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                        new AppException(ErrorCode.WRONG_VERIFY_KEYACTIVE)
                );

//        // Kiểm tra tài khoản bị cấm
//        if(!user.getIsActive()){
//            throw new AppException(ErrorCode.ACCOUNT_IS_BLOCK);
//        }

        // Kiểm tra số lần verify
        if(user.getVerifyNumberOfTimes() <= 0){
            throw new AppException(ErrorCode.VERIFY_CODE_EXPIRED);
        }

        // Kiểm tra mã xác thực
        if(user.getVerifyCode().equals(code)){
            user.setIsVerify(true);
        }else {
            user.setIsVerify(false);
            user.setVerifyNumberOfTimes(user.getVerifyNumberOfTimes() - 1);
            throw new AppException(ErrorCode.WRONG_VERIFY_KEYACTIVE);
        }
        userRepository.save(user);
        log.info(user.toString());
    }

    public TokenResponse refreshToken(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);

        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED)
        );
        if (jwtService.isValidToken(refreshToken, user)) {
            String newAccessToken  = jwtService.generateToken(user);
            String newRefreshToken = jwtService.gennerateRefreshToken(user);
            log.info(user.getRoles().toString());
            List<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());

            return TokenResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .roles(roles)
                    .userId(user.getId())
                    .build();
        }
        throw new AppException(ErrorCode.TOKEN_INVALID);
    }
}








