package by.bntu.Kuzmenok.Teachcourse.controller;

import by.bntu.Kuzmenok.Teachcourse.dto.UserDto;
import by.bntu.Kuzmenok.Teachcourse.entity.User;
import by.bntu.Kuzmenok.Teachcourse.mappers.api.UserMapper;
import by.bntu.Kuzmenok.Teachcourse.security.MyUserDetails;
import by.bntu.Kuzmenok.Teachcourse.security.UserDetailsServiceImpl;
import by.bntu.Kuzmenok.Teachcourse.service.api.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class UserController {


    private final UserService userService;
    private final UserMapper userMapper;



    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("")
    public String getUserPage(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
        User user = userDetails.getUser();
        model.addAttribute("user", userMapper.toDto(user));
        return "profile";
    }

    @PatchMapping("/edit")

    public String editUser(@AuthenticationPrincipal MyUserDetails userDetails,
                           @ModelAttribute("user") @Valid UserDto userDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "profile";
        } else {
            userDetails.setUser(userService.save(userMapper.toEntity(userDto)));
        }
        return "redirect:/profile";
    }
}
