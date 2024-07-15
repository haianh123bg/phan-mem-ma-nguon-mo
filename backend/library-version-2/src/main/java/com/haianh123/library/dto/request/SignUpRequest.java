package com.haianh123.library.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "Tên người dùng không được bỏ trống")
    private String name;

    @NotBlank(message = "Tên tài khoản không được bỏ trống")
    @Email( message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Mật khẩu không được bỏ trống")
    private String password;
}
