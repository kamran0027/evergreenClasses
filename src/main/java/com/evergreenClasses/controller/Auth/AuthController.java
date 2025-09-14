package com.evergreenClasses.controller.Auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.evergreenClasses.Dto.LoginDto;
import com.evergreenClasses.Dto.UserDto;
import com.evergreenClasses.model.User;
import com.evergreenClasses.services.JwtService;
import com.evergreenClasses.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute ("userDto") LoginDto loginDto, HttpServletResponse response, Model model) {
        Optional<User> user = userService.authenticate(loginDto);
        if (user.isPresent()) {
            String token = jwtService.generateToken(loginDto.getEmail());

            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            return "redirect:/admin";
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    @GetMapping("/admin")
    public String successPage(@CookieValue(value = "jwt", defaultValue = "") String jwt, Model model) {
        if (jwt.isEmpty() || !jwtService.validateToken(jwt)) {
            return "redirect:/login";
        }
        String emailId = jwtService.extractEmailId(jwt);
        model.addAttribute("emailId", emailId);
        return "admin_home";
    }

    @GetMapping("/custom-logout")
    public String logout(HttpServletResponse response, Model model) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        response.addCookie(cookie);
        model.addAttribute("logout", "Thanks For Visiting");

        // return "redirect:/public?logout=true";
        return "redirect:/public?logout=true";
    }
}

