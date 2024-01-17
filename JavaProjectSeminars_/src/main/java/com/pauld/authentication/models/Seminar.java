package com.pauld.authentication.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name ="seminars")
public class Seminar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "First name is required!")
	@Size(min = 3, max = 30, message = "Seminar name must be between 3 and 30 characters")
	private String seminarName;

	@NotNull(message = "Seminar date is required!")
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date seminarDate;

	@NotBlank(message = "Location is required!")
	@Size(message = "Please enter a valid location!")
	private String seminarLocation;

	@NotBlank(message = "State is required!")
	@Size(message = "Please enter a valid state!")
	private String seminarState;

	// Foreign Key = "seminarowner_id"
	// add many to one here
	// get getters and setter as well
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="seminarowner_id")
	private User seminarowner;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "participants",
    		joinColumns = @JoinColumn(name="seminar_id"),
    		inverseJoinColumns = @JoinColumn(name="user_id")
    )
    
   private List<User> users;
	

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public Seminar() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSeminarName() {
		return seminarName;
	}

	public void setSeminarName(String seminarName) {
		this.seminarName = seminarName;
	}

	public Date getSeminarDate() {
		return seminarDate;
	}

	public void setSeminarDate(Date seminarDate) {
		this.seminarDate = seminarDate;
	}

	public String getSeminarLocation() {
		return seminarLocation;
	}

	public void setSeminarLocation(String seminarLocation) {
		this.seminarLocation = seminarLocation;
	}

	public String getSeminarState() {
		return seminarState;
	}

	public void setSeminarState(String seminarState) {
		this.seminarState = seminarState;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getSeminarowner() {
		return seminarowner;
	}

	public void setSeminarowner(User seminarowner) {
		this.seminarowner = seminarowner;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	
	

}
