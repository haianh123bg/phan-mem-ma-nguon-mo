package com.haianh123.library.mapper;

import com.haianh123.library.dto.request.SignUpRequest;
import com.haianh123.library.dto.response.SignUpInfoResponse;
import com.haianh123.library.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(SignUpRequest request);
    SignUpInfoResponse toSignUpInfoResponse(User user);
}
