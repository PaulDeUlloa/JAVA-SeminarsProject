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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
    
@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="First name is required!")
    @Size(min=3, max=30, message="First name must be between 3 and 30 characters")
    private String firstName;
    
    @NotBlank(message="Last name is required!")
    @Size(min=3, max=30, message="Last name must be between 3 and 30 characters")
    private String lastName;
    
    @NotBlank(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotBlank(message="Location is required!")
    @Size(message="Please enter a valid location!")
    private String location;
    
    //////////////NEED TO FIX STATE VALIDATION
    
    @NotBlank(message="State is required!")
    @Size(message="Please enter a valid state!")
    private String state;
    
    @NotBlank(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;
    
    @Transient //<- Transient, virtual attribute that will not get stored in our DATABASE
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    private String confirm;
    
    //One to many must match with foreign key in Seminar.Java
    @OneToMany(mappedBy="seminarowner", fetch=FetchType.LAZY)
    private List<Seminar> ownedPosting;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "participants",
    		joinColumns = @JoinColumn(name = "user_id"),
    		inverseJoinColumns = @JoinColumn(name = "seminar_id")
    )
    private List<Seminar> seminars;
    
    
    
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
  
    public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Seminar> getOwnedPosting() {
		return ownedPosting;
	}

	public void setOwnedPosting(List<Seminar> ownedPosting) {
		this.ownedPosting = ownedPosting;
	}

	public List<Seminar> getSeminars() {
		return seminars;
	}

	public void setSeminars(List<Seminar> seminars) {
		this.seminars = seminars;
	}
    
    
  
}
    
