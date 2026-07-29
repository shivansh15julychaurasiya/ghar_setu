package com.setu.service;

import com.setu.dto.request.SendOtpRequest;
import com.setu.dto.request.VerifyOtpRequest;
import com.setu.dto.response.SendOtpResponse;
import com.setu.dto.response.VerifyOtpResponse;

public interface OtpService {

    SendOtpResponse sendOtp(SendOtpRequest request);

    VerifyOtpResponse verifyOtp(VerifyOtpRequest request);

}