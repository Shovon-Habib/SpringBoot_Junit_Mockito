package com.example.test.web;

import com.example.test.model.User;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class DefaultController {

    private final UserService userService;

    @Autowired
    public DefaultController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home");
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/new_user")
    public String newUser(Model model) {
        model.addAttribute("title", "New User");
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        System.out.println(user.getName());
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", bindingResult.getFieldError().getDefaultMessage());
            return "newUser";
        }
        redirectAttributes.addFlashAttribute("message", userService.createUser(user));
        return "redirect:/new_user";
    }
}
