package com.dwalldorf.trackmytime.service;

import com.dwalldorf.trackmytime.config.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private SecurityUser getCurrentUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String getCurrentUserId() {
        return getCurrentUser().getId();
    }

}