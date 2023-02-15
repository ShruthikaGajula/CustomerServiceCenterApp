package com.customerService.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.customerService.app.Entity.Solution;
import com.customerService.app.Exception.IssueIdNotFoundException;
import com.customerService.app.Exception.SolutionException;
import com.customerService.app.Service.SolutionService;
/************************************************************************************
 * @author        Pisariwar Sameeksha
 
 * Description    It is a Test class that provides the Testing methods for adding a new Solution, 
                  Updating existing Solution details ,Fetching Details of Solution by issue Id, Fetching all Solution Details,
                  deleting solution using issue Id.
       
 *Version         1.0  
        
 *Created Date    10-FEB-2023
 ************************************************************************************/
@SpringBootTest
class SolutionTest {
	
	@Autowired
	private SolutionService solutionService;
	
	
	@Test
	void updateSolutionTest() throws SolutionException {
		assertNull(solutionService.updateSolution(new Solution(4,"Solution for the issue")));
	}
	
	@Test
	void updateSolutionTest1() throws SolutionException {
		assertNull(solutionService.updateSolution(new Solution(3,"Solution for the issue")));
	}
	
	@Test
	void updateSolutionTest2() throws SolutionException {
		assertNull(solutionService.updateSolution(new Solution(2,"Solution for the issue")));
	}
	
	@Test
	void updateSolutionExceptionTest() {
		assertThrows(SolutionException.class,()->solutionService.updateSolution(new Solution(10,"XYZ")));
	}
	
	@Test
	void updateSolutionExceptionTest1() {
		assertThrows(SolutionException.class,()->solutionService.updateSolution(new Solution(5,"XYZ")));
	}
	
	@Test
	void updateSolutionExceptionTest2() {
		assertThrows(SolutionException.class,()->solutionService.updateSolution(new Solution(2,"XYZ")));
	}
	
	@Test 
	void updateSolutionExceptionMessageTest() {
		
		String msg=null;
		try {
			solutionService.updateSolution(new Solution(100,"Solution for 100"));
		}
		catch (SolutionException e) {
			msg=e.getMessage();
		}
		assertEquals("Invalid Solution deatils", msg);
	}
	
	@Test
	void getSolutionByIssueIdTest() throws IssueIdNotFoundException{
		assertNotNull(solutionService.getSolutionByIssueId(2));
	}
	
	@Test
	void getSolutionByIssueIdTest1() throws IssueIdNotFoundException{
		assertNotNull(solutionService.getSolutionByIssueId(3));
	}
	
	@Test
	void getSolutionByIssueIdTest2() throws IssueIdNotFoundException{
		assertNotNull(solutionService.getSolutionByIssueId(4));
	}
	
	@Test
	void getSolutionByIssueIdExceptionTest() {
		assertThrows(IssueIdNotFoundException.class,()->solutionService.getSolutionByIssueId(70));
	}
	
	@Test
	void getSolutionByIssueIdExceptionTest1() {
		assertThrows(IssueIdNotFoundException.class,()->solutionService.getSolutionByIssueId(50));
	}
	
	@Test
	void getSolutionByIssueIdExceptionTest2() {
		assertThrows(IssueIdNotFoundException.class,()->solutionService.getSolutionByIssueId(60));
	}
	
	@Test 
	void getSolutionByIssueIdExceptionMessageTest() {
		
		String msg=null;
		try {
			solutionService.getSolutionByIssueId(100);
		}
		catch (IssueIdNotFoundException e) {
			msg=e.getMessage();
		}
		assertEquals("Issue ID is not found", msg);
	}
	
	@Test
	void getAllSolutionTest()
	{
		assertNotNull(solutionService.getAllSolutionList());
	}
	

	
	@Test
	void deleteSolutionByIssueIdExceptionTest() 
	{
		assertThrows(SolutionException.class, ()->solutionService.deleteSolutionByIssueId(1));
	}
	
	@Test
	void deleteSolutionByIssueIdExceptionTest1() 
	{
		assertThrows(SolutionException.class, ()->solutionService.deleteSolutionByIssueId(2));
	}
	

	@Test
	void deleteSolutionByIssueIdExceptionTest2() 
	{
		assertThrows(IssueIdNotFoundException.class, ()->solutionService.deleteSolutionByIssueId(100));
	}
	
	@Test
	void deleteSolutionByIssueIdExceptionTest3() 
	{
		assertThrows(IssueIdNotFoundException.class, ()->solutionService.deleteSolutionByIssueId(20));
	}
	
	@Test 
	void deleteSolutionExceptionMessageTest() {
		
		String msg=null;
		String msg1 = null;
		try {
			solutionService.deleteSolutionByIssueId(2);
		}
		catch (SolutionException e) {
			msg=e.getMessage();
		}
		catch(IssueIdNotFoundException e) {
			msg1 = e.getMessage();
		}
		assertEquals("Solution does not exist for Issue ID", msg);
		assertEquals("Issue does not exist for Issue ID",msg1);
	}

}
