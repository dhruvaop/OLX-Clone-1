package com.clone.olx.controller;

import com.clone.olx.model.AppUser;
import com.clone.olx.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("")
public class RegistrationController {

    private final AppUserService appUserService;

    public RegistrationController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("app_user", new AppUser());
        return "registration-form";
    }

    @GetMapping("/registration2")
    public String showRegistrationForm2(Model model) {
        model.addAttribute("app_user", new AppUser());
        return "registration-form-2";
    }

    @PostMapping("/registration/success")
    public String processRegistration(@Validated AppUser appUser, RedirectAttributes redirectAttributes) {
        try {
            appUserService.signUpUser(appUser);
        } catch (Exception e) {
            redirectAttributes.addAttribute("registration-failed", true);
            return "redirect:/registration";
        }
        return "registration-success";
    }


}
