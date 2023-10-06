package org.launchcode.controllers;

import org.launchcode.data.UserData;
import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.openmbean.CompositeData;
import java.util.Collection;

@Controller
@RequestMapping("user")
public class UserController {
    @GetMapping("/add")
    public String displayAddUserForm(){
        return "user/add";
    }

    @PostMapping("/index")
    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {
    model.addAttribute("user", user);
    model.addAttribute("verify",verify);
    model.addAttribute("username",user.getUsername());
    model.addAttribute("email",user.getEmail());
    model.addAttribute("users", UserData.getAll());
    UserData.addUser(user.getId(),user);

    if (user.getPassword().equals(verify)) {
        System.out.println("Pw check got seen");
        return "user/index";

    } else {
        model.addAttribute("error","Passwords do not match");
        System.out.println("Else got seen");
        return "user/add";
    }
    }

//    @PostMapping("/index")
//    public String renderUserGreetForm(Model model, @ModelAttribute User user) {
//        model.addAttribute("username",user.getUsername());
//        return "user/index";
//    }



}
