package com.pauld.authentication.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.pauld.authentication.models.LoginUser;
import com.pauld.authentication.models.Seminar;
import com.pauld.authentication.models.User;
import com.pauld.authentication.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	// This method will be called from the controller
	// whenever a user submits a registration form.
	public User register(User newUser, BindingResult result) {

		// TO-DO - Reject values:
		// Reject if email is taken (present in database)
		// 1. Find user in the DB by email
		Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
		// 2. if the email is present , reject
		// Unique is the type of errors
		if (optionalUser.isPresent()) {
			result.rejectValue("email", "Unique", "Email is already registed!");
		}
		// Reject if password doesn't match confirmation
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
		}
		// if result has errors, return
		if (result.hasErrors()) {
			return null;
		}
		// Hash and set password, save user to database
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userRepo.save(newUser);

	}

	// This method will be called from the controller
	// whenever a user submits a login form.
	public User login(LoginUser newLogin, BindingResult result) {

		// TO-DO - Reject values:

		// Find user in the DATABASE by email
		Optional<User> optionalUser = userRepo.findByEmail(newLogin.getEmail());
		// 1. Find user in the DB by email again but opposite

		// 2. if the email is not present , reject
		if (optionalUser.isEmpty()) {
			result.rejectValue("email", "Unique", "Email is NOT registed!");
			// return null, due to us telling them not to continue if we can't find a user.
			return null;
		}
		// 3.1 grab the user from potential user
		User user = optionalUser.get();
		// 3.2 if BCrypt password match fails.
		// ** 1st, newLogin.getPassword must be from our form form. **2nd,
		// user.getPassword is the user from the DATABASE
		if (!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid Password!");
		}
		// 4 if result has errors,return. Otherwise, return the user object
		if (result.hasErrors()) {
			return null;
		}
		// otherwise, return the user object
		return user;
	}

	// One Logged In User
	public User oneLoggedInUser(Long id) {
		Optional<User> optionalUser = userRepo.findById(id);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}