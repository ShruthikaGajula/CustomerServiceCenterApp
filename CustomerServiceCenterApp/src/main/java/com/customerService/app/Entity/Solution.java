package com.customerService.app.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/************************************************************************************
 * @author        Sameeksha Pisariwar
 
 * Description    It is a Solution Entity class which is a POJO class i.e it has only 
                  data Members,Constructors,and their respective Getters and Setters.
                  No Business Logic is there in this class.
 *Version         1.0                
 *Created Date    09-FEB-2023
 ************************************************************************************/
@Entity
public class Solution {
	
	@Id
	private Integer solutionId;
	@NotBlank(message = "Issue Status is mandatory!")
	private String solutionDescription;
	public Solution() {
		super();
	}
	public Solution(Integer solutionId, String solutionDescription) {
		super();
		this.solutionId = solutionId;
		this.solutionDescription = solutionDescription;

	}
	public Integer getSolutionId() {
		return solutionId;
	}
	public void setSolutionId(Integer solutionId) {
		this.solutionId = solutionId;
	}
	public String getSolutionDescription() {
		return solutionDescription;
	}
	public void setSolutionDescription(String solutionDescription) {
		this.solutionDescription = solutionDescription;
	}
	
	

}
