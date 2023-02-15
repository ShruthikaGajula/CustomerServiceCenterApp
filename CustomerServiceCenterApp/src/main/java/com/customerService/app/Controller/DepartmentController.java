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

import com.customerService.app.Entity.Department;
import com.customerService.app.Exception.DepartmentException;
import com.customerService.app.Exception.DepartmentIdNotFoundException;
import com.customerService.app.Service.DepartmentService;

/************************************************************************************
 * @author        Siva Sai Mounika Penneru
 
 * Description    It is a Department Controller class where basically the flow of the data is controlled. 
                  It controls the data flow into model object and updates the view whenever data changes.
                  
 *Version         1.0                
 *Created Date    12-FEB-2023
 ************************************************************************************/
@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/department")
	public Department addDepartment(@Valid @RequestBody Department newDepartment) {
		
		return departmentService.addDepartment(newDepartment);
		
	}
	
	@DeleteMapping("/department/{id}")
	public Department deleteDepartment(@PathVariable("id") Integer departmentId) throws DepartmentIdNotFoundException {
		
			return this.departmentService.removeDepartment(departmentId);
		
	}
	
	@PutMapping("/departments")
	public Department modifyDepartment(@RequestBody Department modifyDepartment) {
		try {
			return departmentService.modifyDepartment(modifyDepartment);
		} 
		catch (DepartmentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/department/{id}")
	public Department findDepartmentById(@PathVariable ("id") Integer departmentId) {
		try {
			return departmentService.findDepartmentById(departmentId);
		} 
	catch (DepartmentIdNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/departments")
	public List<Department> getAllDepartments()
	{
		return departmentService.getAllDepartment();
	}

}
