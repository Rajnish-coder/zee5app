package com.zee.zee5app.dto;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
//@ToString(exclude = "UserId")
//@AllArgsConstructor


public class User {
	
	
//	static {
//		System.out.println("hello from static");
//	}
//	
//	{
//		System.out.println("hello from initialization");
//	}
//	
	public User() {
		// TODO Auto-generated constructor stub
		
	}
	
	
public User(String firstName, String lastName, String email) {
		//super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}




public User(String firstName, String lastName, String email, LocalDate doj, LocalDate dob,
		boolean isActive) {
	//super();
	this(firstName,lastName,email,doj,dob);
	this.isActive = isActive;
}





public User(String firstName, String lastName, String email, LocalDate doj, LocalDate dob) {
	//super();
	this(firstName,lastName,email);
//	this.UserId = userId;
	this.doj = doj;
	this.dob = dob;
}





	@Override
	public String toString() {
		return "User [UserId=" + UserId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", doj=" + doj + ", dob=" + dob + ", isActive=" + isActive + "]";
	}
	
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return super.toString();
//	}
	private String UserId;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate doj;
	private LocalDate dob;
	private boolean isActive;
	//public static final int MAX_SIZE=10;
//	@Override
//	public int compareTo(User o) {
//		// TODO Auto-generated method stub
//		
//		return this.UserId.compareTo(o.UserId);
//	}
	@Override
	public int hashCode() {
		return Objects.hash(UserId, dob, doj, email, firstName, isActive, lastName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(UserId, other.UserId) && Objects.equals(dob, other.dob) && Objects.equals(doj, other.doj)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& isActive == other.isActive && Objects.equals(lastName, other.lastName);
	}
	
	
//	public String getUserId() {
//		return UserId;
//	}
//	public void setUserId(String userId) {
//		UserId = userId;
//	}
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public LocalDate getDoj() {
//		return doj;
//	}
//	public void setDoj(LocalDate doj) {
//		this.doj = doj;
//	}
//	public LocalDate getDob() {
//		return dob;
//	}
//	public void setDob(LocalDate dob) {
//		this.dob = dob;
//	}
//	public boolean isActive() {
//		return isActive;
//	}
//	public void setActive(boolean isActive) {
//		this.isActive = isActive;
//	}

}
