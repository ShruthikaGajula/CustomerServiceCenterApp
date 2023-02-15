package com.customerService.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerService.app.Entity.Issue;
import com.customerService.app.Exception.IssueException;
import com.customerService.app.Repository.IssueRepository;

/************************************************************************************
 * @author        Pisariwar Sameeksha
 
 * Description    It is a Service class that provides the service methods for adding a new Issue, 
                  Updating existing Issue details ,Fetching Details of a Issue by issue Id,
                  Fetching all Issue Details, Assigning Issue to Operator By Id.
                  
 *Version         1.0 
                
 *Created Date    09-FEB-2023
 ************************************************************************************/
@Service
public class IssueServiceImplementation implements IssueService{
	
	@Autowired
	private IssueRepository issueRepository;
	
	/************************************************************************************
	 * Method: 			           addIssue
	 
     * Description: 			   To add the new Issue Details
     
	 * @param addIssue:            Issue object is passed
	 
	 * @returns Issue:             Returns object of Issue if successfully details Are added otherwise throws Exception
	                               
	 * @throws IssueException:     It is raised if Issue Id is already exists in the database
	                               
     * Created By                  Pisariwar Sameeksha
     
     * Created Date                09-FEB-2023                           
    ************************************************************************************/
	@Override
	public Issue addIssue(Issue issue) throws IssueException {
		
		Optional<Issue> getIssue = issueRepository.findById(issue.getIssueId());
		if(getIssue.isPresent())
		{
			throw new IssueException("Cannot create new issue as ID already exists");
		}

		return issueRepository.save(issue);
	}
	
	/************************************************************************************
	 * Method: 			           getIssueById
	 
     * Description: 			   To fetch the Issue Details using Issue Id
     
	 * @param getIssueById:        issueId of type Integer is passed
	 
	 * @returns Issue:             Returns object of Issue if successfully details are fetched otherwise throws Exception
	                               
	 * @throws IssueException:     It is raised if Issue Id is not found in the database
	                               
     * Created By                  Pisariwar Sameeksha
     
     * Created Date                09-FEB-2023                           
    ************************************************************************************/
	@Override
	public Issue getIssueById(Integer IssueId) throws IssueException {
		Optional<Issue> optIssue = issueRepository.findById(IssueId);
		if(optIssue.isEmpty()) {
			throw new IssueException("Issue Id Not Found");
		}
		return optIssue.get();
		
	}
	
	/************************************************************************************
	 * Method: 			           updateCustomerIssue
	 
     * Description: 			   To update the existing Issue Details
     
	 * @param updateCustomerIssue: Issue object is passed
	 
	 * @returns Issue:             Returns object of Issue if successfully details are updated otherwise throws Exception
	                               
	 * @throws IssueException:     It is raised if Issue Id is not found  in the database
	                               
     * Created By                  Pisariwar Sameeksha
     
     * Created Date                09-FEB-2023                           
    ************************************************************************************/
	@Override
	public Issue updateCustomerIssue(Issue issue) throws IssueException{
		Optional<Issue> optIssue = issueRepository.findById(issue.getIssueId());
		if(optIssue.isEmpty()) {
			throw new IssueException("Invalid Issue details");
		}
		return issueRepository.save(issue);
	}
	
	/************************************************************************************
	 * Method: 			           getAllIssues
	 
     * Description: 			   To fetch the list of all the issues
     
	 * @param getAllIssues:        No parameter is passed
	 
	 * @returns List<Issue>:       Returns List of object of Issues
	                               
	 * @throws Exception:          No exception is thrown
	                               
     * Created By                  Pisariwar Sameeksha
     
     * Created Date                09-FEB-2023                           
    ************************************************************************************/
	@Override
	public List<Issue> getAllIssues() {
		// TODO Auto-generated method stub
		return this.issueRepository.findAll();
	}


}
