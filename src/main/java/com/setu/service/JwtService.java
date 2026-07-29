package com.setu.service;

import com.setu.entity.User;

//package com.gharsetu.service;



public interface JwtService {

    String generateToken(User user);

    String extractMobile(String token);

    boolean validateToken(String token);

}
