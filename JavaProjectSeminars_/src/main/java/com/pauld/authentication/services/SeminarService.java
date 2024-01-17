package com.pauld.authentication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pauld.authentication.models.Seminar;
import com.pauld.authentication.models.User;
import com.pauld.authentication.repositories.SeminarRepository;
import com.pauld.authentication.repositories.UserRepository;

@Service
public class SeminarService {

	@Autowired
	private SeminarRepository seminarRepo;

	// Find all
	public List<Seminar> allSeminars() {
		return seminarRepo.findAll();
	}

	// FIND ONE - future note, label this "findOneSmeinar"
	public Seminar oneSeminar(Long id) {
		Optional<Seminar> optionalSeminar = seminarRepo.findById(id);
		if (optionalSeminar.isPresent()) {
			return optionalSeminar.get();
		} else {
			return null;
		}
	}

	// Create

	public Seminar createSeminar(Seminar newSeminar) {
		return seminarRepo.save(newSeminar);
	}

	// Update

	public Seminar updateSeminar(Seminar oneSeminar) {
		return seminarRepo.save(oneSeminar);
	}

	// Delete

	public void deleteSeminar(Long id) {
		seminarRepo.deleteById(id);
	}

	// Add a user into a Seminar
	public Seminar addUserToSeminar(Long seminarId, User user) {
		Seminar seminar = this.oneSeminar(seminarId);
		seminar.getUsers().add(user);
		return seminarRepo.save(seminar);
	}

	// Leave a user from a Seminar
	public Seminar removeUserFromSeminar(Long seminarId, User user) {
		Seminar seminar = this.oneSeminar(seminarId);
		seminar.getUsers().remove(user);
		return seminarRepo.save(seminar);
	}

}
