package com.nnk.springboot.utils;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.interfaces.IUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RequiredArgsConstructor
public class UtilSession {
    private static IUser userService;

    public static void intiSession(HttpSession session, Principal principal){
        if (principal.getName() == null)
            throw new IllegalArgumentException("The name cannot be null");

        User userBDD = userService.getUserByName(principal.getName());
        session.setAttribute("user", userBDD);
    }
}
