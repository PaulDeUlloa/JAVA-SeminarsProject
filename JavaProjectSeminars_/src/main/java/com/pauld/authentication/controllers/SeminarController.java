package com.pauld.authentication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.pauld.authentication.models.Seminar;
import com.pauld.authentication.models.User;
import com.pauld.authentication.services.SeminarService;
import com.pauld.authentication.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class SeminarController {

	// TO DO: import service
	@Autowired
	private SeminarService seminarService;

	@Autowired
	private UserService userService;

	@Autowired
	private HttpSession session;

//	@GetMapping("/seminars")
//	public String index(Model model) {
//		List<Seminar> seminars = seminarService.allSeminars();
//		model.addAttribute("seminars", seminars);
//		return "seminarDashboard.jsp";
//	}

	@GetMapping("/seminars")
	public String renderHomeDashboard(HttpSession session, Model model, Long id) {
		// Route protection
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}

		Long userId = (Long) session.getAttribute("userId");

		User loggedInUser = userService.oneLoggedInUser(userId);

		// put it in Model model
		model.addAttribute("loggedInUser", loggedInUser);

		List<Seminar> seminarList = seminarService.allSeminars();

		// import Model model and List to get dot notation drop down
		// the "seminarList" gets used in our items, in seminarDashboard.jsp
		model.addAttribute("seminarList", seminarList);
		model.addAttribute("newSeminar", new Seminar());
		
		//Test
		User user = userService.oneLoggedInUser(userId);
		List<Seminar> joinedSeminars = user.getSeminars();
		model.addAttribute("joinedSeminars", joinedSeminars);

		return "seminarDashboard.jsp";
	}


	// POST Main Page Create Form Display
	// Must redirect on post request
	@PostMapping("/seminars")
	public String processCreateSeminar(@Valid @ModelAttribute("newSeminar") Seminar newSeminar, BindingResult result,
			Model model, Long id) {
		// Route protection
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}

		if (result.hasErrors()) {
			// how to keep
			List<Seminar> seminarList = seminarService.allSeminars();
			model.addAttribute("seminarList", seminarList);
			
			return "seminarDashboard.jsp";
		} else {
			seminarService.createSeminar(newSeminar);

			return "redirect:/seminars";
		}
	}

	// Details Page: get id from path, get details from service, put it in Model
	// model

	@GetMapping("/seminars/{id}")
	public String seminarDetailsPage(@PathVariable("id") Long id, Model model) {

		// Route protection
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		//Test
		Long userId = (Long) session.getAttribute("userId");
		// get details from service
		Seminar oneSeminar = seminarService.oneSeminar(id);
		// put it in Model model
		model.addAttribute("oneSeminar", oneSeminar);
		
		User user = userService.oneLoggedInUser(userId);
		List<Seminar> joinedSeminars = user.getSeminars();
		model.addAttribute("joinedSeminars", joinedSeminars);
		return "seminarDetails.jsp";
	}

	// EDIT Page:
	@GetMapping("/seminars/{id}/edit")
	public String renderEditSeminarForm(@PathVariable("id") Long id, Model model) {

		// Route protection
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}

		// copied from details page, same code.
		Seminar oneSeminar = seminarService.oneSeminar(id);
		model.addAttribute("oneSeminar", oneSeminar);
		return "editSeminar.jsp";
	}

	// UPDATE Page:
	@PutMapping("/seminars/{id}/edit")
	public String processEditSeminar(@Valid @ModelAttribute("oneSeminar") Seminar oneSeminar, BindingResult result) {

		// Route protection
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		//validation
		if (result.hasErrors()) {
			return "editSeminar.jsp";
		} else {
			seminarService.updateSeminar(oneSeminar);
			return "redirect:/seminars";
		}
	}

	// Join Seminar:
	@PutMapping("/seminars/{id}/join")
	public String joinSeminar(@PathVariable("id")Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.oneLoggedInUser(userId);
		seminarService.addUserToSeminar(id, user);
		return "redirect:/seminars";
	}
	
	// Leave Joined Seminar:
		@PutMapping("/seminars/{id}/leave")
		public String leaveSeminar(@PathVariable("id")Long id, HttpSession session) {
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.oneLoggedInUser(userId);
			seminarService.removeUserFromSeminar(id, user);
			return "redirect:/seminars";
		}

	// DELETE
	@DeleteMapping("/seminars/{id}")
	public String processDelete(@PathVariable("id") Long id) {
		seminarService.deleteSeminar(id);
		return "redirect:/seminars";
	}

}
