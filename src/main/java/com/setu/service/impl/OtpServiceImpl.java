package com.setu.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.setu.dto.request.SendOtpRequest;
import com.setu.dto.request.VerifyOtpRequest;
import com.setu.dto.response.SendOtpResponse;
import com.setu.dto.response.VerifyOtpResponse;
import com.setu.entity.OtpVerification;
import com.setu.entity.User;
import com.setu.exception.BadRequestException;
import com.setu.exception.ResourceNotFoundException;
import com.setu.repository.OtpVerificationRepository;
import com.setu.repository.UserRepository;
import com.setu.service.OtpService;
import com.setu.util.OtpUtil;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class OtpServiceImpl implements OtpService {
	

    private final UserRepository userRepository;
    private final OtpVerificationRepository otpVerificationRepository;
	
	  @Override
	    public SendOtpResponse sendOtp(SendOtpRequest request) {

	        User user = userRepository.findByMobile(request.getMobile())
	                .orElseThrow(() ->
	                        new ResourceNotFoundException("User not found with this mobile number."));
	        
	        otpVerificationRepository
	        .findByMobileAndPurposeAndVerifiedFalse(
	                request.getMobile(),
	                "LOGIN")
	        .ifPresent(otpVerificationRepository::delete);

	        String otp = OtpUtil.generateOtp();

	        OtpVerification otpVerification = new OtpVerification();

	        otpVerification.setMobile(user.getMobile());
	        otpVerification.setOtp(otp);
	        otpVerification.setPurpose("LOGIN");
	        otpVerification.setVerified(false);
	        otpVerification.setExpiresAt(LocalDateTime.now().plusMinutes(5));

	        otpVerificationRepository.save(otpVerification);

	        return SendOtpResponse.builder()
	                .mobile(user.getMobile())
	                .otp(otp)          // Development only
	                .expiresInSeconds(300L)
	                .build();
	    }
	    
	    @Override
	    @Transactional
	    public VerifyOtpResponse verifyOtp(VerifyOtpRequest request) {

	        User user = userRepository.findByMobile(request.getMobile())
	                .orElseThrow(() ->
	                        new ResourceNotFoundException("User not found."));

	        OtpVerification otpVerification = otpVerificationRepository
	                .findTopByMobileAndPurposeAndVerifiedFalseOrderByCreatedAtDesc(
	                        request.getMobile(),
	                        "LOGIN")
	                .orElseThrow(() ->
	                        new BadRequestException("OTP not found."));

	        if (otpVerification.getExpiresAt().isBefore(LocalDateTime.now())) {
	            throw new BadRequestException("OTP has expired.");
	        }

	        if (!otpVerification.getOtp().equals(request.getOtp())) {
	            throw new BadRequestException("Invalid OTP.");
	        }

	        otpVerification.setVerified(true);
	        otpVerificationRepository.save(otpVerification);

	        if (!Boolean.TRUE.equals(user.getMobileVerified())) {
	            user.setMobileVerified(true);
	            userRepository.save(user);
	        }

	        return VerifyOtpResponse.builder()
	                .userId(user.getId())
	                .firstName(user.getFirstName())
	                .mobile(user.getMobile())
	                .verified(true)
	                .build();
	    }
	    

}
