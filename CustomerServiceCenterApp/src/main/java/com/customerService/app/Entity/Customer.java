package com.customerService.app.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/************************************************************************************
 * @author        Shaswat Jain
 
 * Description    It is a Customer Entity class which is a POJO (Plain Old Java Object) class i.e it has only 
                  data Members,Constructors,and their respective Getters and Setters.
                  No Business Logic is there in this class.
 *Version         1.0                
 *Created Date    09-FEB-2023
 ************************************************************************************/
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	@NotNull
	@Pattern(regexp="[a-zA-Z]{3,}",message="Name can only Contain Alphabet and Should have minimun 3 characters")
	private String firstName;
	@NotNull
	@Pattern(regexp="[a-zA-Z]{3,}",message="Name can only Contain Alphabet and Should have minimun 3 characters")
	private String lastName;
	
	private String email;
	@NotNull
    @Pattern(regexp="^[6-9][0-9]{9}$",message="Mobile Number only starts with 6-9 ,Should have 10 Digits and it contains only numeric")
	private String mobileNumber;
	@NotNull
	@Pattern(regexp="[a-zA-Z]{3,}",message="City can only Contain Alphabet and Should have Minimum 3 characters")
	private String city;
	@OneToMany(fetch = FetchType.EAGER)
	private List<Call> callList = new ArrayList<>();
	
	public Customer() {
		super();
	}
	public Customer(Integer customerId, String firstName, String lastName, String email, String mobile, String city) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobile;
		this.city = city;
	}
	public Customer(String firstName, String lastName, String email, String mobileNumber, String city) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber= mobileNumber;
		this.city = city;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobile) {
		this.mobileNumber = mobile;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<Call> getCallList() {
		return callList;
	}
	public void setCallList(List<Call> callList) {
		this.callList = callList;
	}

}
