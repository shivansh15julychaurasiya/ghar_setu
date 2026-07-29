package com.setu.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('OWNER')")
    public String dashboard() {
        return "Welcome Owner";
    }

}