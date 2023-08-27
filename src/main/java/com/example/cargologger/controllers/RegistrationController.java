package com.example.cargologger.controllers;

import com.example.cargologger.models.CredentialsGenerator;
import com.example.cargologger.models.users.Driver;
import com.example.cargologger.models.users.Employer;
import com.example.cargologger.models.users.User;
import com.example.cargologger.repositories.LoginRepository;
import com.example.cargologger.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        CredentialsGenerator credentialsGenerator = new CredentialsGenerator(loginRepository);
        try {
            String loginId = credentialsGenerator.generateLoginId();
            String password = credentialsGenerator.generatePassword(10);
            model.addAttribute("loginId", loginId);
            model.addAttribute("password", password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "registration";
    }

    @PostMapping("/registration")
    public String getUserData(@ModelAttribute User user, @RequestParam("role") String role, HttpSession httpSession) {
        if (role.equals("manager")) {
            Employer employer = new Employer();
            employer.setName(user.getName());
            employer.setSurname(user.getSurname());
            employer.setPhoneNumber(user.getPhoneNumber());
            employer.setEmail(user.getEmail());
            userRepository.save(employer);
            httpSession.setAttribute("employer", employer);
            return "redirect:/main/current_1";
        } else {
            Driver driver = new Driver();
            driver.setName(user.getName());
            driver.setSurname(user.getSurname());
            driver.setPhoneNumber(user.getPhoneNumber());
            driver.setEmail(user.getEmail());
            userRepository.save(driver);
            httpSession.setAttribute("driver", driver);
            return "redirect:/main/current_2";
        }
    }
}
