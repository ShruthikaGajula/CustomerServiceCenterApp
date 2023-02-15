package com.customerService.app.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customerService.app.Entity.Call;
import com.customerService.app.Entity.Customer;
import com.customerService.app.Exception.CallException;
import com.customerService.app.Exception.CustomerException;
import com.customerService.app.Service.CustomerService;

/************************************************************************************
 * @author        Shaswat Jain
 
 * Description    It is a Customer Controller class where basically the flow of the data is controlled. 
                  It controls the data flow into model object and updates the view whenever data changes.
                  
 *Version         1.0                
 *Created Date    12-FEB-2023
 ************************************************************************************/
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customer")
	public String createCustomer(@RequestBody @Valid Customer addCustomer)throws CustomerException
	{
		Customer newCustomer=customerService.addCustomer(addCustomer);
		return "Customer Id: "+newCustomer.getCustomerId()+" Details Added Successfully";
	}
		
	@PutMapping("/customer")
	public String  updateCustomer(@RequestBody @Valid Customer updateCustomer)throws CustomerException
	{
		Customer updatedCustomer=customerService.updateCustomer(updateCustomer);
		return "Customer Id:"+updatedCustomer.getCustomerId()+" Details Updated Successfully";
	}
	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable("id") Integer customerId)throws CustomerException
	{
		return customerService.getCustomerById(customerId);
	}
	
	@DeleteMapping("/customer/{id}")
	public String deleteCustomerById(@PathVariable("id") Integer customerId)throws CustomerException
	{
		Customer getCustomer=customerService.deleteCustomerById(customerId);
		return "Customer Id:"+customerId+" Details Deleted Successfully";
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomer()
	{
		return customerService.getAllCustomer();
	}
	
	@GetMapping("/calls/{id}")
	public List<Call> getAllCallOfCustomer(@PathVariable("id") Integer customerId)throws CustomerException,CallException
	{
		return customerService.getAllCallsOfCustomerById(customerId);
	}
}
