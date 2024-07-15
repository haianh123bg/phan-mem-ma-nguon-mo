package com.haianh123.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RefreshTokenRequest {

    @NotBlank(message = "Token không được bỏ trống")
    private String refreshToken;
}
