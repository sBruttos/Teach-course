package by.bntu.Kuzmenok.Teachcourse.controller;

import by.bntu.Kuzmenok.Teachcourse.entity.User;
import by.bntu.Kuzmenok.Teachcourse.security.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserDetailsServiceImpl userDetailsService;

    public RegistrationController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("userForm", new User());
        return "util/registration";
    }

    @PostMapping("/registration")
    public String makeRegistration(@ModelAttribute("userForm") @Valid User user,
                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "util/registration";
        }

        if (!userDetailsService.saveNewUser(user)) {
            bindingResult.addError(new FieldError("userForm", "email",
                    "User with this email is already exists"));
            return "util/registration";
        }
        userDetailsService.saveNewUser(user);
        return "redirect:/login";
    }
}
