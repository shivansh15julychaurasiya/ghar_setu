package com.setu.dto.response;

//package com.gharsetu.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private String accountStatus;

}
