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

import com.customerService.app.Entity.Operator;
import com.customerService.app.Exception.DepartmentIdNotFoundException;
import com.customerService.app.Exception.NoOperatorsListFoundException;
import com.customerService.app.Exception.OperatorIdNotFoundException;
import com.customerService.app.Service.OperatorService;

/************************************************************************************
 * @author        Likhitha Geddam
 
 * Description    It is a Operator Controller class where basically the flow of the data is controlled. 
                  It controls the data flow into model object and updates the view whenever data changes.
                  
 *Version         1.0                
 *Created Date    12-FEB-2023
 ************************************************************************************/

@RestController 
//to create RESTful web services using Spring MVC
public class OperatorController {
	
	@Autowired
	private OperatorService operatorService;
	
	@PostMapping(value = "/operator/{deptId}")
	public String addOperatorToDepartmentById(@Valid @RequestBody Operator newOperator, @PathVariable("deptId") Integer departmentId) throws DepartmentIdNotFoundException {
		operatorService.addOperatorToDepartmentById(newOperator, departmentId);
		return "Operator ID: "+newOperator.getOperatorId()+" Detalis Added Successfully";
	}
	
	@GetMapping(value = "/operator/{opId}")
	public Operator getOperatorById(@PathVariable("opId") Integer operatorId) throws OperatorIdNotFoundException {
		
		return operatorService.getOperatorById(operatorId);
		
	}
	
	@GetMapping(value = "/operators/{deptId}")
	public List<Operator> getAllOperatorsByDepartmentId(@PathVariable("deptId") Integer departmentId) throws DepartmentIdNotFoundException, NoOperatorsListFoundException{
		
		return operatorService.getOperatorsByDepartmentId(departmentId);
	}
	
	@GetMapping(value = "/operators")
	public List<Operator> getAllOperators() throws NoOperatorsListFoundException{
		
		return operatorService.getAllOperators();
	}
	
	
	@PutMapping(value = "/operator/{opId}")
	public String updateOperatorById(@PathVariable("opId") Integer operatorId, @RequestBody Operator updateOperator) throws OperatorIdNotFoundException {
		operatorService.updateOperatorById(operatorId, updateOperator);
		return "Operator ID:"+operatorId+" Details Updated Successfully";
	}
	
	@DeleteMapping(value = "/operator/{deptId}/{opId}")
	public String deleteOperatorFromDepartmentById(@PathVariable("deptId") Integer departmentId, @PathVariable("opId") Integer operatorId) throws DepartmentIdNotFoundException, OperatorIdNotFoundException { 
		this.operatorService.deleteOperatorFromDepartmentById(departmentId, operatorId);
		return "Operator ID:"+operatorId+" Details Deleted Successfully";
	}

}
