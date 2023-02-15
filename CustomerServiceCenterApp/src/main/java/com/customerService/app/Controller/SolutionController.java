package com.customerService.app.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customerService.app.Entity.Solution;
import com.customerService.app.Exception.IssueIdNotFoundException;
import com.customerService.app.Exception.SolutionException;
import com.customerService.app.Service.SolutionService;

/************************************************************************************
 * @author        Sameeksha Pisariwar
 
 * Description    It is a Solution Controller class where basically the flow of the data is controlled. 
                  It controls the data flow into model object and updates the view whenever data changes.
                  
 *Version         1.0                
 *Created Date    12-FEB-2023
 ************************************************************************************/
@RestController
public class SolutionController {
	
	@Autowired
	private SolutionService solutionService;
	
	@PostMapping("/solution/{issueId}")
	public String createSolution(@Valid @RequestBody Solution solution, @PathVariable("issueId") Integer issueId) throws IssueIdNotFoundException 
	{
		Solution newSolution = solutionService.addSolutionByIssueId(solution, issueId);
		return "Solution is added for issue Id: "+issueId;
		
	}

	
	@GetMapping("/solution/{issueId}")
	public Optional<Solution> getSolution(@PathVariable("issueId") Integer issueId) throws IssueIdNotFoundException
	{
			return solutionService.getSolutionByIssueId(issueId);
	}
	
	
	@PutMapping("/solution")
	public String updateSolution(@Valid @RequestBody Solution newSolution) throws SolutionException 
	{
		Solution solution = solutionService.updateSolution(newSolution);
		return "Details solution Id: "+solution.getSolutionId()+" updated successfully";
		
	}
	
	
	@GetMapping("/solutions")
	public List<Solution> getListOfSolution(){
		return solutionService.getAllSolutionList();
	}
	
	
	@DeleteMapping("/solution/{issueId}")
	public String deleteSolutionByIssueId(@PathVariable("issueId") Integer issueId) throws SolutionException,IssueIdNotFoundException
	{
		Solution solution = solutionService.deleteSolutionByIssueId(issueId);
		return "Solution for Issue Id "+issueId+" deleted successfully";
	}
	
	
	

}
