package com.pauld.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pauld.authentication.models.LoginUser;
import com.pauld.authentication.models.User;
import com.pauld.authentication.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
    
    // Add once service is implemented:
     @Autowired
     private UserService userService;
    
    @GetMapping("/")
    public String index(Model model) {
    
        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "logreg.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
    	// 1. get the registeredUser by calling register in service and make use of the binding result
    	User registeredUser = userService.register(newUser, result);
        // 2. check for result errors
        if(result.hasErrors()) {
        	// 2.1 if there are errors, put the missing model in and return jsp
            model.addAttribute("newLogin", new LoginUser());
            return "logreg.jsp";
        }else {
        	// 2.2 if no errors, set userId in session and redirect
        	session.setAttribute("userId", registeredUser.getId());
        	//THIS IS HOW TO DISPLAY A USERNAME ON PAGE (Display st.1)
        	session.setAttribute("firstName", registeredUser.getFirstName());
        	return "redirect:/seminars";
        }
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
    	 // 1. get the user by calling login in service and make use of the binding result
    	User loggedInUser = userService.login(newLogin, result);
        // 2. check for result errors    
        if(result.hasErrors()) {
        	// 2.1 if there are errors, put the missing model in and return jsp
            model.addAttribute("newUser", new User());
            return "logreg.jsp";
        }else {
        	// 2.2 if no errors, get the user info from user and set userId in the session
        	session.setAttribute("userId", loggedInUser.getId());
        	//THIS IS HOW TO DISPLAY A USERNAME ON PAGE (Display st.2)
        	session.setAttribute("firstName", loggedInUser.getFirstName());
        	return "redirect:/seminars";
        }
    }
    
    @GetMapping("/logout")
    public String processLogout(HttpSession session) {
    	//destroys every single session inside our jsp
    	session.removeAttribute("userId");
    	//(Display st.3)
    	session.removeAttribute("firstName");
    	return "redirect:/";
    }

    
}
