package com.customerService.app.Service;

import java.util.List;

import com.customerService.app.Entity.Issue;
import com.customerService.app.Exception.IssueException;
import com.customerService.app.Exception.OperatorIdNotFoundException;

public interface IssueService {
	
	Issue addIssue(Issue issue) throws IssueException;
	
	Issue getIssueById(Integer IssueId) throws IssueException;
	
	Issue updateCustomerIssue(Issue issue) throws IssueException;
	
	List<Issue> getAllIssues() ;

	//Issue addIssueToOperatorById(Issue assignIssue, Integer operatorId) throws OperatorIdNotFoundException;
	

}
