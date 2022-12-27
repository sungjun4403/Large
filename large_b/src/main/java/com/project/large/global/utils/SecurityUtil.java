package com.project.large.global.utils;

import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class SecurityUtil {

    public static String getLoginedUserGitId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        return name;
    }
}
