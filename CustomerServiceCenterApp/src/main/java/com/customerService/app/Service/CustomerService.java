package com.customerService.app.Service;

import java.util.List;

import com.customerService.app.Entity.Call;
import com.customerService.app.Entity.Customer;
import com.customerService.app.Exception.CallException;
import com.customerService.app.Exception.CustomerException;

public interface CustomerService {
	
	Customer addCustomer(Customer addCustomer)throws CustomerException;
	
	Customer updateCustomer(Customer updateCustomer)throws CustomerException;
	
	Customer getCustomerById(Integer customerId) throws CustomerException;

	Customer deleteCustomerById(Integer customerId) throws CustomerException;

	List<Customer> getAllCustomer();
	
	List<Call> getAllCallsOfCustomerById(Integer customerId) throws CustomerException,CallException;

}
