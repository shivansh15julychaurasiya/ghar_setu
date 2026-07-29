package com.setu.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.setu.entity.OtpVerification;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {

    Optional<OtpVerification> findTopByMobileOrderByCreatedAtDesc(String mobile);

}
