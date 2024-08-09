package com.sivaganapathi.app.rest.Controller;

import com.sivaganapathi.app.rest.Models.User;
import com.sivaganapathi.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ApiControllers {
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPage(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userRepo.save(user);
        return "redirect:/";
    }

    @PostMapping(value = "/update/{id}")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute("user") User updatedUser) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setOccupation(updatedUser.getOccupation());
            user.setAge(updatedUser.getAge());
            userRepo.save(user);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userRepo.deleteById(id);
        return "redirect:/";
    }
}