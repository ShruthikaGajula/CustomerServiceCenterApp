package com.customerService.app.Service;

import java.util.List;
import java.util.Optional;

import com.customerService.app.Entity.Solution;
import com.customerService.app.Exception.IssueIdNotFoundException;
import com.customerService.app.Exception.SolutionException;

public interface SolutionService {
	
    Solution addSolutionByIssueId(Solution solution, Integer issueId) throws IssueIdNotFoundException;
	
	Optional<Solution> getSolutionByIssueId(Integer issueId) throws IssueIdNotFoundException;
	
	Solution updateSolution(Solution solution) throws SolutionException;
	
	List<Solution> getAllSolutionList();
	
	Solution deleteSolutionByIssueId(Integer issueId) throws SolutionException,IssueIdNotFoundException;

}
