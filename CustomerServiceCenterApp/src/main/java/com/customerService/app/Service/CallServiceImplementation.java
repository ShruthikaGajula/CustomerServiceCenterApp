package com.customerService.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerService.app.Entity.Call;
import com.customerService.app.Entity.Customer;
import com.customerService.app.Entity.Issue;
import com.customerService.app.Exception.CallIdNotFoundException;
import com.customerService.app.Exception.CallListNotFoundException;
import com.customerService.app.Exception.CustomerException;
import com.customerService.app.Repository.CallRepository;
import com.customerService.app.Repository.CustomerRepository;
import com.customerService.app.Repository.IssueRepository;

/************************************************************************************
 * @author        Shruthika Gajula
 
 * Description    It is a Call class that provides the service methods for adding call details to customer, 
 				  raising issue through that call id, Fetching Details of a issue by call Id, Fetching all 
 				  call Details, Fetching call list by customer id, Fetching call details by call id, 
 				  Deleting call list by customer id, Deleting call record by call id.
                  
 *Version         1.0 
                
 *Created Date    09-FEB-2023
 ************************************************************************************/

@Service
public class CallServiceImplementation implements CallService{
	
	@Autowired
	private CallRepository callRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private IssueRepository issueRepository;
	
	/************************************************************************************
	 * Method: 			                    addCallDetailsToCustomerTable
	 
     * Description: 			            To map call details to customer id
     
	 * @param addCallDetailsToCustomerTable:call object and customer Id of type Integer is passed
	 
	 * @returns Call:                   	Returns object of Call successfully if details are added otherwise throws Exception
	                               
	 * @throws CustomerException: 	        It is raised if Customer Id is not found in the database
	                               
     * Created By                        	Shruthika Gajula
     
     * Created Date                      	09-FEB-2023                           
    ************************************************************************************/
	
	@Override
	public Call addCallDetailsToCustomerTable(Call newCall, Integer customerId) throws CustomerException{
		
		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if(optCustomer.isEmpty()) {
			throw new CustomerException("Customer not found for customer id: "+customerId);
		}
		Customer foundCustomer = optCustomer.get();
		Call call = callRepository.save(newCall);
		foundCustomer.getCallList().add(call);
		customerRepository.save(foundCustomer);
		return call;
	}
	
	/************************************************************************************
	 * Method: 			            	assignIssueToCall
	 
     * Description: 			    	To assign issue to call id
     
	 * @param assignIssueToCall:		issue object and call Id of type Integer is passed
	 
	 * @returns Issue:           		Returns object of Issue successfully if details are added otherwise throws Exception
	                               
	 * @throws CallIdNotFoundException: It is raised if Call Id is not found in the database
	                               
     * Created By                   	Shruthika Gajula
     
     * Created Date                 	09-FEB-2023                           
    ************************************************************************************/
	
	@Override
	public Issue assignIssueToCall(Issue newIssue, Integer callId) throws CallIdNotFoundException{
		Optional<Call> optCall=callRepository.findById(callId);
		if(optCall.isEmpty()) {
			throw new CallIdNotFoundException("Call not found for call id: "+callId);
		}
		Call foundCall=optCall.get();
		Issue issue=issueRepository.save(newIssue);
		foundCall.setIssues(issue);
		callRepository.save(foundCall);
		return issue;
	}
	
	/************************************************************************************
	 * Method: 			            	getCallById
	 
     * Description: 			    	To fetch call details by call id
     
	 * @param getCallById:				call Id of type Integer is passed
	 
	 * @returns Call:           		Returns object of call successfully if call details are fetched otherwise throws Exception
	                               
	 * @throws CallIdNotFoundException: It is raised if Call Id is not found in the database
	                               
     * Created By                   	Shruthika Gajula
     
     * Created Date                 	09-FEB-2023                           
    ************************************************************************************/
	
	@Override
	public Call getCallById(Integer callId) throws CallIdNotFoundException {
		
		Optional<Call> optCall=callRepository.findById(callId);
		if(optCall.isEmpty()) {
			throw new CallIdNotFoundException("Call not found for call id: "+callId);
		}
		return optCall.get();
	}
	
	/************************************************************************************
	 * Method: 			            	 getCallListByCustomerId
	 
     * Description: 			    	 To fetch call list details by customer id
     
	 * @param getCallListByCustomerId:	 customer Id of type Integer is passed
	 
	 * @returns List<Call>:           	 Returns call list details of customer successfully if call list details are fetched otherwise throws Exception
	                               
	 * @throws CustomerException: 		 It is raised if Customer Id is not found in the database
	 
	 * @throws CallListNotFoundException:It is raised if call list is empty for provided customer id in the database
	                               
     * Created By                   	 Shruthika Gajula
     
     * Created Date                 	 09-FEB-2023                           
    ************************************************************************************/
	
	@Override
	public List<Call> getCallListByCustomerId(Integer customerId) throws CustomerException, CallListNotFoundException {
		
		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if(optCustomer.isEmpty()) {
			throw new CustomerException("Customer not found for customer id: "+customerId);
		}
		List<Call> foundCallList = this.callRepository.findAll();
		if(foundCallList.isEmpty()) {
			throw new CallListNotFoundException("Call list is empty for customer id: "+customerId);
		}
		return this.callRepository.findAll();
	}
	
	/************************************************************************************
	 * Method: 			            	 getAllCalls
	 
     * Description: 			    	 To fetch all calls 
     
	 * @param :	 						 No parameter is passed
	 
	 * @returns List<Call>:           	 Returns all call list details of all the customers successfully if all call list details are fetched otherwise throws Exception
	                               
	 * @throws 							 No exception was thrown
	                               
     * Created By                   	 Shruthika Gajula
     
     * Created Date                 	 09-FEB-2023                           
    ************************************************************************************/
	
	public List<Call> getAllCalls() {
		
		return this.callRepository.findAll();
	}
	
	/************************************************************************************
	 * Method: 			            	getIssueByCallId
	 
     * Description: 			    	To fetch issue details by call id
     
	 * @param getIssueByCallId:			call Id of type Integer is passed
	 
	 * @returns Issue:           		Returns object of issue successfully if issue details are fetched otherwise throws Exception
	                               
	 * @throws CallIdNotFoundException: It is raised if Call Id is not found in the database
	                               
     * Created By                   	Shruthika Gajula
     
     * Created Date                 	09-FEB-2023                           
    ************************************************************************************/
	
	@Override
	public Issue getIssueByCallId(Integer callId) throws CallIdNotFoundException{
		
		Optional<Call> call=callRepository.findById(callId);
		if(call.isEmpty()) {
			throw new CallIdNotFoundException("Call not found for call id: "+callId);
		}
		return call.get().getIssues();
	}
	
	/************************************************************************************
	 * Method: 			            	 deleteCallByCustomerId
	 
     * Description: 			    	 To delete one call record by customer id
     
	 * @param deleteCallByCustomerId:	 customer id and call Id of type Integers are passed
	 
	 * @returns Call:           		 Returns object of Call successfully if Call details are fetched otherwise throws Exception
	 
	 * @throws CustomerException:		 It is raised if customer id is not found
	 		
	 * @throws CallListNotFoundException:It is raised if Call List is empty in the database
	                               
     * Created By                   	 Shruthika Gajula
     
     * Created Date                 	 09-FEB-2023                           
    ************************************************************************************/
	
	@Override
	public Call deleteCallByCustomerId(Integer customerId,Integer callId) throws CustomerException, CallListNotFoundException{
		
		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if(optCustomer.isEmpty()) {
			throw new CustomerException("Customer not found for customer id: "+customerId);
		}
		Customer foundCustomer = optCustomer.get();
		Optional<Call> optCall = this.callRepository.findById(callId);
		if(optCall.isEmpty()) {
			throw new CallListNotFoundException("Call list is empty for customer id: "+customerId);
		}
		Call foundCall=optCall.get();
		foundCustomer.getCallList().remove(foundCall);
		this.callRepository.delete(foundCall);
		this.customerRepository.save(foundCustomer);
		return foundCall;
	}
	
	/************************************************************************************
	 * Method: 			            	 deleteCallListByCustomerId
	 
     * Description: 			    	 To delete call list by customer id
     
	 * @param deleteCallListByCustomerId:customer id of type Integer is passed
	 
	 * @returns List<Call>:           	 Returns object of Call List successfully if entire Call List details are fetched otherwise throws Exception
	 
	 * @throws CustomerException:		 It is raised if customer id is not found
	 		
	 * @throws CallListNotFoundException:It is raised if Call List is empty in the database
	                               
     * Created By                   	 Shruthika Gajula
     
     * Created Date                 	 09-FEB-2023                           
    ************************************************************************************/
	
	@Override
	public List<Call> deleteCallListByCustomerId(Integer customerId) throws CustomerException, CallListNotFoundException {
		
		Optional<Customer> optCustomer=this.customerRepository.findById(customerId);
		if(optCustomer.isEmpty()){
			throw new CustomerException("Customer not found for customer id: "+customerId);
		}
		Customer foundCustomer=optCustomer.get();
		List<Call> callList=foundCustomer.getCallList();
		if(callList==null) {
			throw new CallListNotFoundException("Call list is empty for customer id: "+customerId);
		}
		foundCustomer.setCallList(null);
		for(Call call:callList) {
		    this.callRepository.delete(call);
		}
		return callList;
	}
}
