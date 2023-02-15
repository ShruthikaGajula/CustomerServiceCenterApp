package com.customerService.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerService.app.Entity.Call;
import com.customerService.app.Entity.Customer;
import com.customerService.app.Exception.CallException;
import com.customerService.app.Exception.CustomerException;
import com.customerService.app.Repository.CustomerRepository;

/************************************************************************************
 * @author        Shaswat Jain
 
 * Description    It is a Service class that provides the service methods for adding a new Customer, 
                  Updating existing Customer details ,Deleting Customer details By Id, Fetching Details of a Customer by Id,
                  Fetching all Customers Details, Fetching all call details of a Customer By Id.
                  
 *Version         1.0                
 *Created Date    09-FEB-2023
 ************************************************************************************/

@Service
public class CustomerServiceImplementation implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	
	
	/************************************************************************************
		 * Method: 			           addCustomer
	     *Description: 			       To add the new Customer Details
	     
		 * @param addCustomer          Customer object is passed
		 * @returns Customer           Returns object of Customer if successfully details Are added otherwise 
		                               throws Exception
		                               
		 * @throws CustomerException   It is raised if Customer Email or Mobile Number is Already registered with
		                               some other Customer.
	                               
	     *Created By                   Shaswat Jain
	     *Created Date                 09-FEB-2023                           
		 
	 ************************************************************************************/
	
	@Override
	public Customer addCustomer(Customer addCustomer) throws CustomerException{
		
		Optional<Customer> getCustomerByEmail=customerRepository.findByEmail(addCustomer.getEmail());
		if(getCustomerByEmail.isPresent())
		{
			throw new CustomerException("Cannot create new Customer As Email Already Exists");
		}
		
		Optional<Customer> getCustomerByMobileNumber=customerRepository.findByMobileNumber(addCustomer.getMobileNumber());
		if(getCustomerByMobileNumber.isPresent())
		{
			throw new CustomerException("Cannot create new Customer As Mobile Number Already Exists");
		}
		return customerRepository.save(addCustomer);
	}
	
	/************************************************************************************
	 * Method: 			           updateCustomer
     *Description: 			       To update the existing Customer Details
     
	 * @param updateCustomer       Customer object is passed
	 * @returns Customer           Returns object of Customer if successfully details Are updated otherwise 
	                               throws Exception
	                               
	 * @throws CustomerException   It is raised if Customer Id is not Found or  Customer Email or Mobile Number which we are modifying
	                               is Already registered with some other Customer.
	                                                        
     *Created By                   Shaswat Jain
     *Created Date                 09-FEB-2023                           
	 
   ************************************************************************************/

	@Override
	public Customer updateCustomer(Customer updateCustomer) throws CustomerException {
		
		Optional<Customer> getCustomerById=customerRepository.findById(updateCustomer.getCustomerId());
		if(getCustomerById.isEmpty())
		{
			throw new CustomerException("Cannot Update Customer Details As Customer Id not found");
		}
		
		
		Optional<Customer> getCustomerByEmail=customerRepository.findByEmail(updateCustomer.getEmail());
		if(getCustomerByEmail.isPresent())
		{
			if(getCustomerByEmail.get().getCustomerId()!=updateCustomer.getCustomerId())
			{
				throw new CustomerException("Cannot Update Customer Details As Email Already Exists");
			}
		}
		
		Optional<Customer> getCustomerByMobileNumber=customerRepository.findByMobileNumber(updateCustomer.getMobileNumber());
		if(getCustomerByMobileNumber!=null)
		{
			if(getCustomerByMobileNumber.get().getCustomerId()!=updateCustomer.getCustomerId())
			{
				throw new CustomerException("Cannnot Update Customer Details As Mobile Number Already Exists");
			}
		}
		return customerRepository.save(updateCustomer);
	}
	
	/************************************************************************************
	 * Method: 			           getCustomerById
     *Description: 			       To fetch the existing Customer Details by CustomerID.
     
	 * @param updateCustomer       CustomerId of type Integer is passed
	 * @returns Customer           Returns object of Customer if successfully details are fetched otherwise 
	                               throws Exception
	                               
	 * @throws CustomerException   It is raised if Customer Id is not Found.
     *Created By                   Shaswat Jain
     *Created Date                 09-FEB-2023                           
	 
    **************************************************************************************/
	@Override
	public Customer getCustomerById(Integer customerId) throws CustomerException {
		
		Optional<Customer> getCustomer=customerRepository.findById(customerId);
		if(getCustomer.isEmpty())
		{
			throw new CustomerException("Customer ID Not Found");
		}
		return getCustomer.get();
		
	}
    
	/************************************************************************************
	 * Method: 			           deleteCustomerById
     *Description: 			       To delete the existing Customer Details by CustomerID.
     
	 * @param updateCustomer       CustomerId of type Integer is passed
	 * @returns Customer           Returns object of Customer if successfully details are deleted otherwise 
                               throws Exception
	                               
	 * @throws CustomerException   It is raised if Customer Id is not Found.
     *Created By                   Shaswat Jain
     *Created Date                 09-FEB-2023                           
	 
    **************************************************************************************/
	@Override
	public Customer deleteCustomerById(Integer customerId) throws CustomerException {
		
		Optional<Customer> getCustomer=customerRepository.findById(customerId);
		if(getCustomer.isEmpty())
		{
			throw new CustomerException("Invalid Customer Details");
		}
		customerRepository.deleteById(customerId);
		return getCustomer.get();
	}

	/************************************************************************************
	 * Method: 			           getAllCustomer
     *Description: 			       To fetch the details of all the Customers
     
	 * @returns List<Customer>     Returns List of objects of type Customer
	                          
     *Created By                   Shaswat Jain
     *Created Date                 09-FEB-2023                           

    **************************************************************************************/

	@Override
	public List<Customer> getAllCustomer() {
		
		return customerRepository.findAll();
	}
	
	/************************************************************************************
	 * Method: 			           getAllCallsOfCustomerById
     *Description: 			       To fetch the existing Customer all call details by CustomerID.
     
	 * @param updateCustomer       CustomerId of type Integer is passed
	 * @returns Customer           Returns List of Object of type Call if successfully details are fetched otherwise 
		                               throws Exception
	                               
	 * @throws CustomerException   It is raised if Customer Id is not found.
	 * @throws CallException       It is raised if Call Id is not found.
     *Created By                   Shaswat Jain
     *Created Date                 09-FEB-2023                           
	 
    **************************************************************************************/
	
	@Override
	public List<Call> getAllCallsOfCustomerById(Integer customerId)throws CustomerException,CallException
	{
		Optional<Customer> getCall=customerRepository.findById(customerId);
		if(getCall.isEmpty())
		{
			throw new CustomerException("ID Not Found");
		}
		if(getCall.get().getCallList().isEmpty())
		{
			throw new CallException("Call Details Not Found");
		}
		return getCall.get().getCallList();
	}

}