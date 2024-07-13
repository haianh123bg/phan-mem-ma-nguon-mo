package com.haianh123.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignInRequest {

    @NotBlank(message = "Tên tài khoản không được bỏ trống")
    private String email;

    @NotBlank(message = "Mật khẩu không được bỏ trống")
    private String password;
}
