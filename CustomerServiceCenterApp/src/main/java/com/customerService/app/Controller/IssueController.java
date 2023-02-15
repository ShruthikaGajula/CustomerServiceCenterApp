package com.customerService.app.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customerService.app.Entity.Issue;
import com.customerService.app.Exception.IssueException;
import com.customerService.app.Exception.OperatorIdNotFoundException;
import com.customerService.app.Service.IssueService;

/************************************************************************************
 * @author        Sameeksha Pisariwar
 
 * Description    It is a Issue Controller class where basically the flow of the data is controlled. 
                  It controls the data flow into model object and updates the view whenever data changes.
                  
 *Version         1.0                
 *Created Date    12-FEB-2023
 ************************************************************************************/
@RestController
public class IssueController {
	
	@Autowired
	private IssueService issueService;
	
	
	@PostMapping("/issue")
	public String addIssue(@Valid @RequestBody Issue issue) throws IssueException 
	{
		Issue newIssue = issueService.addIssue(issue);
		return "Details of issue Id: "+newIssue.getIssueId()+" added successfully";
	}
	
	
	@GetMapping("/issue/{issueId}")
	public Issue getIssueByIssueId(@PathVariable("issueId") Integer issueId) throws IssueException 
	{
			return issueService.getIssueById(issueId);
		
	}
	
	
	@PutMapping("/issue")
	public String updateIssue(@Valid @RequestBody Issue updateIssue) throws IssueException
	{
		Issue newIssue =  issueService.updateCustomerIssue(updateIssue);
		return "Details of issue Id: "+newIssue.getIssueId()+" updated successfully";
	}
	
	
	@GetMapping("/issues")
	public List<Issue> getListOfIssues()
	{
			return issueService.getAllIssues();
	}
}


