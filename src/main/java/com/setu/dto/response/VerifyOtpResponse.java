package com.setu.dto.response;

//package com.gharsetu.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyOtpResponse {

    private Long userId;

    private String firstName;

    private String mobile;

    private boolean verified;

}