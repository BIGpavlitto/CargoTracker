package com.example.cargologger.controllers;

import com.example.cargologger.models.LoginAuthentication;
import com.example.cargologger.models.encryption.PasswordHash;
import com.example.cargologger.models.users.Employer;
import com.example.cargologger.models.users.User;
import com.example.cargologger.repositories.DriverRepository;
import com.example.cargologger.repositories.LoginRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
public class LoginController {
    private final LoginRepository loginRepository;
    private StopwatchController stopwatchController;
    private final DriverRepository driverRepository;
    @Autowired
    public LoginController(LoginRepository loginRepository, DriverRepository driverRepository,StopwatchController stopwatchController) {
        this.loginRepository = loginRepository;
        this.driverRepository = driverRepository;
        this.stopwatchController = stopwatchController;
    }

    @ModelAttribute
    public void addPreloadedHashedPasswords() {
//        String pass_1 = PasswordHash.hashPassword("QntuyT");
//        String pass_2 = PasswordHash.hashPassword("UbsfsfT");
//        System.out.println(pass_1);
//        System.out.println(pass_2);
    }

    @GetMapping("/login")
    public String showRegistration() {
        stopwatchController.reset();
        stopwatchController.stop();
        return "login";
    }

    @PostMapping("/login")
    public String loginControl(Model model, @RequestParam("loginID") String loginID, @RequestParam("password") String password, HttpSession session) {
        stopwatchController.start();
        Optional<LoginAuthentication> loginDataWrapper = this.loginRepository.findByLoginId(loginID);

        if (loginDataWrapper.isPresent()) {
            String hashedPassword = loginDataWrapper.get().getPassword();
            if (hashedPassword != null) {
                if (PasswordHash.checkPassword(password, hashedPassword)) {
                    User user = loginDataWrapper.get().getUser();
                    if (user != null) {
                        if (user instanceof Employer) {
                            Employer employer = (Employer) user;
                            employer.setDrivers(driverRepository.findByEmployer_Id(user.getId()));
                            session.setAttribute("employer", employer);
                            session.setAttribute("time", stopwatchController.getTime());
                            return "redirect:/main/current_1";
                        }else{
                            session.setAttribute("driver", user);
                            session.setAttribute("time", stopwatchController.getTime());
                            return "redirect:/main/current_2";
                        }
                    }
                } else {
                    model.addAttribute("errorMessage", "Wrong password. Try once more!");
                    model.addAttribute("login", loginID);
                    model.addAttribute("password", password);
                    return "login";
                }
            }
        } else {
            model.addAttribute("errorMessage", "User not found! Check userID!");
            model.addAttribute("login", loginID);
            model.addAttribute("password", password);
            return "login";
        }
        return null;
    }
}
