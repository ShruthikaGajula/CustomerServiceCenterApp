package com.customerService.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerService.app.Entity.Issue;
import com.customerService.app.Entity.Solution;
import com.customerService.app.Exception.IssueIdNotFoundException;
import com.customerService.app.Exception.SolutionException;
import com.customerService.app.Repository.IssueRepository;
import com.customerService.app.Repository.SolutionRepository;

/************************************************************************************
 * @author        Pisariwar Sameeksha
 
 * Description    It is a Service class that provides the service methods for adding a new Solution, 
                  Updating existing Solution details ,Fetching Details of a Solution by issue Id,
                  Fetching all Solution Details.
                  
 *Version         1.0 
                
 *Created Date    09-FEB-2023
 ************************************************************************************/

@Service
public class SolutionServiceImplementation implements SolutionService{
	
	@Autowired
	private SolutionRepository solutionRepository;
	@Autowired
	private IssueRepository issueRepository;

	/************************************************************************************
	 * Method: 			                 addSolutionByIssue
	 
     * Description: 			         To add the Solution by Issue Id
     
	 * @param addSolutionByIssueId:      Solution object and issue Id of type Integer is passed
	 
	 * @returns Solution:                Returns object of Solution successfully if details are added otherwise throws Exception
	                               
	 * @throws IssueIdNotFoundException: It is raised if Issue Id is not found in the database
	                               
     * Created By                        Pisariwar Sameeksha
     
     * Created Date                      09-FEB-2023                           
    ************************************************************************************/
	
	@Override
	public Solution addSolutionByIssueId(Solution solution, Integer issueId) throws IssueIdNotFoundException {
		
		Optional<Issue> optIssue = this.issueRepository.findById(issueId);
		if(optIssue.isEmpty()) {
			throw new IssueIdNotFoundException("Issue does not exist for this ID");
		}
		Issue foundIssue = optIssue.get();
		Solution addSolution = this.solutionRepository.save(solution);
		foundIssue.setSolutions(addSolution);
		this.issueRepository.save(foundIssue);
		return addSolution;
	}
	
	/************************************************************************************
	 * Method: 			                    getSolutionByIssueId
	 
     * Description: 			            To fetch the Solution Details using Issue Id
     
	 * @param getSolutionByIssueId:         issueId of type Integer is passed
	 
	 * @returns Solution:                   Returns object of Solution successfully if details are fetched otherwise throws Exception
	                               
	 * @throws IssueIdNotFoundException:    It is raised if Issue Id is not found in the database
	                               
     * Created By                           Pisariwar Sameeksha
     
     * Created Date                         09-FEB-2023                           
    ************************************************************************************/

	@Override
	public Optional<Solution> getSolutionByIssueId(Integer issueId) throws IssueIdNotFoundException {
		
		Optional<Solution> existingSolution = solutionRepository.findById(issueId);
		if(existingSolution.isEmpty())
		{
			throw new IssueIdNotFoundException("Issue ID is not found");
		}
		return solutionRepository.findById(issueId);
	}
	
	/************************************************************************************
	 * Method: 			           updateSolution
	 
     * Description: 			   To update the existing Solution Details
     
	 * @param updateSolution:      Solution object is passed
	 
	 * @returns Solution:          Returns object of Solution if successfully details are updated otherwise throws Exception
	                               
	 * @throws SolutionException:  It is raised if solution Id is not found  in the database
	                               
     * Created By                  Pisariwar Sameeksha
     
     * Created Date                09-FEB-2023                           
    ************************************************************************************/

	@Override
	public Solution updateSolution(Solution solution) throws SolutionException {
		
		Optional<Solution> updateNewSolution = solutionRepository.findById(solution.getSolutionId());
		if(updateNewSolution.isEmpty()) {
			throw new SolutionException("Invalid Solution deatils");
		}

		return solutionRepository.save(solution);
	}
	
	/************************************************************************************
	 * Method: 			           getAllSolutionList
	 
     * Description: 			   To fetch the list of all the solutions
     
	 * @param getAllSolutionList:  No parameter is passed
	 
	 * @returns List<Solution>:    Returns List of object of Solution
	                               
	 * @throws Exception:          No exception is thrown
	                               
     * Created By                  Pisariwar Sameeksha
     
     * Created Date                09-FEB-2023                           
    ************************************************************************************/
	
	@Override
	public List<Solution> getAllSolutionList() {
		
		return solutionRepository.findAll();
	}
	
	/************************************************************************************
	 * Method: 			                deleteSolutionByIssueId
	 
     * Description: 			        To delete the solution using issue Id
     
	 * @param deleteSolutionByIssueId:  Issue Id of type Integer is passed
	 
	 * @returns Solution:               Returns object of deleted Solution if successfully details are deleted otherwise throws Exception
	                               
	 * @throws SolutionException:       It is raised when solution does not exist for the given issue Id
	 
	 * @throws IssueIdNotFoundException:It is raised when Issue Id does not exist     
	                               
     * Created By                       Pisariwar Sameeksha
     
     * Created Date                     09-FEB-2023                           
    ************************************************************************************/

	@Override
	public Solution deleteSolutionByIssueId(Integer issueId) throws SolutionException, IssueIdNotFoundException {
		
		Optional<Issue> optIssue = issueRepository.findById(issueId);
		if(optIssue.isEmpty()) {
			throw new IssueIdNotFoundException("Issue does not exist for Issue ID");
		}
		Issue foundIssue = optIssue.get();
		Solution solution = foundIssue.getSolutions();
		if(solution==null) {
			throw new SolutionException("Solution does not exist for Issue ID");
		}
		foundIssue.setSolutions(null);
		this.solutionRepository.delete(solution);
		return solution;
	}

}
