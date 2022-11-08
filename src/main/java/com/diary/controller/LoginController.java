package com.diary.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(Authentication authentication, HttpServletRequest request, ModelMap model) {
        if (authentication != null) {
            return "redirect:/main";
        }

        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }

        return "login";
    }
}
