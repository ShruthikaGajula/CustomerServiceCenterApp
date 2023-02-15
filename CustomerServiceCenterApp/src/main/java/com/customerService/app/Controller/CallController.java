package com.customerService.app.Controller;

import java.util.List;

/************************************************************************************
 * @author        Shruthika Gajula
 
 * Description    It is a Call Controller class where basically the flow of the data is controlled. 
                  It controls the data flow into model object and updates the view whenever data changes.
                  
 *Version         1.0   
              
 *Created Date    12-FEB-2023
 ************************************************************************************/

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.customerService.app.Entity.Call;
import com.customerService.app.Entity.Customer;
import com.customerService.app.Entity.Issue;
import com.customerService.app.Exception.CallIdNotFoundException;
import com.customerService.app.Exception.CallListNotFoundException;
import com.customerService.app.Exception.CustomerException;
import com.customerService.app.Repository.CustomerRepository;
import com.customerService.app.Repository.IssueRepository;
import com.customerService.app.Service.CallService;
import com.customerService.app.Service.CustomerService;

/************************************************************************************
 * @author        Shruthika Gajula
 
 * Description    It is a Call Controller class where basically the flow of the data is controlled. 
                  It controls the data flow into model object and updates the view whenever data changes.
                  
 *Version         1.0                
 *Created Date    12-FEB-2023
 ************************************************************************************/
@RestController
public class CallController {

	@Autowired
	private CallService callService;
	@Autowired
	private IssueRepository issueRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/call/{customerId}")
	@ResponseStatus(value=HttpStatus.CREATED)
	public String addCallDetailsToCustomerTable(@Valid @RequestBody Call newCall,@PathVariable("customerId") Integer customerId) throws CustomerException{
		
		Call addCall = callService.addCallDetailsToCustomerTable(newCall,customerId);
		return "Call Id :"+addCall.getCallId()+" Successfully added to Customer ID :"+customerId;
	}
	
	@PostMapping("/issue/{callId}")
	@ResponseStatus(value=HttpStatus.CREATED)
	public Issue assignIssueToCall(@Valid @RequestBody Issue newIssue,@PathVariable("callId") Integer callId) throws CallIdNotFoundException{
		
		return callService.assignIssueToCall(newIssue,callId);
	}
	
	@GetMapping("/call/{callId}")
	public Call getCallById(@PathVariable("callId") Integer callId)throws CallIdNotFoundException{
		
		return callService.getCallById(callId);
	}
	
	@GetMapping("/callList/{customerId}")
	public List<Call> getCallListByCustomerId(@PathVariable("customerId") Integer customerId) throws CustomerException, CallListNotFoundException{
			
		return callService.getCallListByCustomerId(customerId);
	}
	
	@GetMapping("/calls")
	public List<Call> getAllCalls(){
		
		return callService.getAllCalls();
	}
	
	@GetMapping("issue/{callId}")
	public Issue getIssueByCallId(@PathVariable("callId") Integer callId) throws CallIdNotFoundException{
		
		return this.callService.getIssueByCallId(callId);
	}
	
	@DeleteMapping("/call/{customerId}/{callId}")
	public Call deleteCallByCallId(@PathVariable("customerId") Integer callId,@PathVariable("callId") Integer customerId) throws CustomerException, CallListNotFoundException{
		
		return this.callService.deleteCallByCustomerId(customerId,callId);
	}
	
	@DeleteMapping("/callList/{customerId}")
	public List<Call> deleteCallListByCustomerId(@PathVariable("customerId") Integer customerId)throws CustomerException, CallListNotFoundException{
		
		return this.callService.deleteCallListByCustomerId(customerId);
	}
}