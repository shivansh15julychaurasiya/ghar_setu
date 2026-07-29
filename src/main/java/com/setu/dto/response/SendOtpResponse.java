package com.setu.dto.response;

//package com.gharsetu.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendOtpResponse {

    private String mobile;

    private String otp;

    private Long expiresInSeconds;

}