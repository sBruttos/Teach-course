package by.bntu.Kuzmenok.Teachcourse.controller.admin;

import by.bntu.Kuzmenok.Teachcourse.dto.UserDto;
import by.bntu.Kuzmenok.Teachcourse.entity.User;
import by.bntu.Kuzmenok.Teachcourse.security.MyUserDetails;
import by.bntu.Kuzmenok.Teachcourse.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("admin/userList")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getAll());
        return "admin/userList";
    }

    @PatchMapping("/{id}/deactivate")
    public String deactivateUser(@PathVariable("id") User user){
        userService.save(userService.deactivate(user));
        return "redirect:/admin/userList";
    }
    @PatchMapping("/{id}/activate")
    public String activateUser(@PathVariable("id") User user){
        userService.save(userService.activate(user));
        return "redirect:/admin/userList";
    }
}
