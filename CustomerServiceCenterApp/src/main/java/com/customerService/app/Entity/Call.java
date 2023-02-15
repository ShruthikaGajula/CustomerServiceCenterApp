package com.customerService.app.Entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/************************************************************************************
 * @author        Shruthika Gajula
 
 * Description    It is a Call Entity class which is a POJO (Plain Old Java Object) class i.e it has only 
                  data Members,Constructors,and their respective Getters and Setters.
                  No Business Logic is there in this class.
 *Version         1.0                
 *Created Date    09-FEB-2023
 ************************************************************************************/
@Entity
public class Call {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer callId;
	@NotNull(message = "Date is mandatory")
	private Date callDate;
	@NotNull(message = "Call duration is mandatory")
	private Double callDuration;
	@Pattern(regexp="[0-9]{10}", message = "Mobile number must contain only numbers and it's size should 10 digits")
	private String phoneNumber;
	@OneToOne
	private Issue issues;
	public Call() {
		super();
	}
	public Call(Integer callId, Date callDate, double callDuration, String phoneNumber) {
		super();
		this.callId = callId;
		this.callDate = callDate;
		this.callDuration = callDuration;
		this.phoneNumber = phoneNumber;
	}
	public Integer getCallId() {
		return callId;
	}
	public void setCallId(Integer callId) {
		this.callId = callId;
	}
	public Date getCallDate() {
		return callDate;
	}
	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}
	public double getCallDuration() {
		return callDuration;
	}
	public void setCallDuration(double callDuration) {
		this.callDuration = callDuration;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Issue getIssues() {
		return issues;
	}
	public void setIssues(Issue issues) {
		this.issues = issues;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(callId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Call other = (Call) obj;
		return Objects.equals(callId, other.callId);
	}
	
}
