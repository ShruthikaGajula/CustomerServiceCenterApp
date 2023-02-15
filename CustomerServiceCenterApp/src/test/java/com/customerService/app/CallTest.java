package com.customerService.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.customerService.app.Entity.Issue;
import com.customerService.app.Exception.CallIdNotFoundException;
import com.customerService.app.Exception.CallListNotFoundException;
import com.customerService.app.Exception.CustomerException;
import com.customerService.app.Repository.CustomerRepository;
import com.customerService.app.Service.CallService;
/************************************************************************************
 * @author        Shruthika Gajula
 
 * Description    It is a Test class that provides the Testing methods for adding a new Call, 
                  Updating existing Call details ,Fetching Details of Call by Call Id, 
                  Fetching Details of Call by Customer Id, Fetching all Calls Details,
                  deleting Call id.
       
 *Version         1.0  
        
 *Created Date    10-FEB-2023
 ************************************************************************************/
@SpringBootTest
class CallTest {
	
	@Autowired
	private CallService callService;
	
	@Autowired
	private CustomerRepository customerRepository;
		
	@Test
	public void getAllCallsTest() {
		
		assertNotNull(callService.getAllCalls());
	} 
	
	@Test
	public void assignIssueToCallTest()throws CallIdNotFoundException {
		
		assertNotNull(callService.assignIssueToCall(new Issue(123,"plan related","expiry","raised"),17));	
	}
	
	@Test
	public void assignIssueToCallExceptionTest(){
		
		assertThrows(CallIdNotFoundException.class,()->callService.getCallById(100));	
	}
	
	@Test
	public void getCallByIdTest()throws CallIdNotFoundException{
		assertNotNull(callService.getCallById(17));
	}
	
	@Test
	public void getCallByIdExceptionTest() {
		
		assertThrows(CallIdNotFoundException.class,()->callService.getCallById(100));
	}
	
	@Test
	public void getIssueByCallIdTest() throws CallIdNotFoundException{
		
		assertNotNull(callService.getIssueByCallId(17));
	}
	
	@Test
	public void getIssueByCallIdExceptionTest() {
		
		assertThrows(CallIdNotFoundException.class, ()->callService.getIssueByCallId(100));
	}
	
	@Test
	public void getCallListByCustIdExceptionTest1() {
		
		assertThrows(CustomerException.class, ()->callService.getCallListByCustomerId(100));
	}
	
	@Test
	public void getCallListByCustIdExceptionTest2() {
		
		assertThrows(CallListNotFoundException.class, ()->callService.getCallListByCustomerId(10));
	}
	
	@Test
	public void getCallListByCustIdTest() throws CustomerException, CallListNotFoundException{
		
		assertNotNull(callService.getCallListByCustomerId(15));
	}
	
//	@Test
//	public void deleteCallByCallIdTest() {
//		String msg=null;
//		try {
//			callService.deleteCallByCallId(1, 17);
//		}
//		catch(CustomerNotFoundException e){
//			msg=e.getMessage();
//		}
//		catch(CallListNotFoundException e) {
//			msg=e.getMessage();
//		}
//	}
	
	@Test
	public void deleteCallByCallIdTest()throws CustomerException, CallListNotFoundException
	{
		assertNotNull(callService.deleteCallByCustomerId(1,17));
	}
	
	@Test
	public void deleteCallByCallIdExceptionTest() {
		assertThrows(CustomerException.class, ()->callService.deleteCallByCustomerId(100,1));
	}
	
	@Test
	public void deleteCallByCallIdExceptionTest2() {
		assertThrows(CallListNotFoundException.class, ()->callService.deleteCallByCustomerId(1,100));
	}
	
	@Test
	public void deleteCallListByCustomerIdExceptionTest() {
		assertThrows(CustomerException.class, ()->callService.deleteCallListByCustomerId(100));
	}
	
	@Test
	public void deleteCallListByCustomerIdExceptionTest2() {
		assertThrows(CallListNotFoundException.class, ()->callService.deleteCallListByCustomerId(10));
	}
	
	@Test 
	public void deleteCallListByCustomerIdExceptionMessageTest() {
		String msg1=null;
		String msg2=null;
		try {
			callService.deleteCallListByCustomerId(100);
		}
		catch(CustomerException e) {
			msg1=e.getMessage();
			assertEquals("Customer not found for customer id: ",msg1);
		}
		catch(CallListNotFoundException e) {
			msg2=e.getMessage();
			assertEquals("Call list is empty for customer id: ",msg2);
		}
	}
}