package com.pauld.authentication.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pauld.authentication.models.Seminar;

@Repository
public interface SeminarRepository extends CrudRepository <Seminar, Long> {
	List <Seminar> findAll();
	
}
