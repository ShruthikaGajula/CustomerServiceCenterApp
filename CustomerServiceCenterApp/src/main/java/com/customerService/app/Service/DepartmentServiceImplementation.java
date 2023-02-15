package com.customerService.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerService.app.Entity.Department;
import com.customerService.app.Exception.DepartmentException;
import com.customerService.app.Exception.DepartmentIdNotFoundException;
import com.customerService.app.Repository.DepartmentRepository;

/***********************************************************************************
* @author        Siva SaiMounika.Penneru
* Description    It is a Service class that provides the service methods for adding a new Department, 
                 Modifying existing Department details ,Deleting Department details By Id, Fetching Details of a Department by Id,
                 Fetching all Department Details.
                 
*Version         1.0                
*Created Date    09-FEB-2023
************************************************************************************/
@Service
public class DepartmentServiceImplementation implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	
	/************************************************************************************
	 * Method: 			           addDepartment
     *Description: 			       To add the new Customer Details
     
	 * @paramaddDepartment         Department object is passed
	 * @returns Department          Returns object of Department if successfully details are added
	                               
	    
	                               
     *Created By                   Siva Sai Mounika.Penneru
     *Created Date                 09-FEB-2023                           
	 
	 ************************************************************************************/
	@Override
	public Department addDepartment(Department department) {

		return departmentRepository.save(department);
	}
	
	/************************************************************************************
	 * Method: 			           removeCustomerById
     *Description: 			       To remove the existing Department Details by DepartmentID.
     
	 * @paramupdateDepartment     DepartmentId of type Integer is passed
	 * @returns Department          Returns object of Department if successfully details are deleted otherwise 
	                               throws Exception
	                               
	 * @throws DepartmentException   It is raised if Department Id is not Found.
     *Created By                   Siva Sai Mounika.Penneru
     *Created Date                 09-FEB-2023                           
	 
    **************************************************************************************/
	@Override
	public Department removeDepartment (Integer departmentId) throws DepartmentIdNotFoundException {
		Optional<Department> optDepartment = this.departmentRepository.findById(departmentId);
				if(optDepartment.isEmpty()) {
					throw new DepartmentIdNotFoundException("Department ID doesn't exist to delete");
				}
				
		Department department = optDepartment.get();
		this.departmentRepository.delete(department);
		return department;
	
	}
	
	/************************************************************************************
	 * Method: 			           modifyDepartment
     *Description: 			       To remove the existing Department Details
     
	 * @paramupdateDepartment       Department object is passed
	 * @returns Department          Returns object of Department if successfully details Are modified  otherwise 
	                               throws Exception
	                               
	 * @throws DepartmentException   It is raised if Department Id  not Found  which we are modifying
	                               is Already registered with some other Department.
	                                                        
     *Created By                   Siva Sai Mounika.Penneru
     *Created Date                 09-FEB-2023                           
	 
   ************************************************************************************/
	@Override
	
	public Department modifyDepartment(Department modifyDepartment) throws DepartmentException {
		Department foundDepartment= departmentRepository.save(modifyDepartment);
		if(foundDepartment==null)
		{
			throw new DepartmentException("Department Id Not Found");
		}
		return this.departmentRepository.save(modifyDepartment);
	}   

	public List<Department> getAllDepartment() {
		
		return this.departmentRepository.findAll();
	
	}
	/************************************************************************************
	 * Method: 			           getAllDepartment
     *Description: 			       To fetch the details of all the Departments
     
	 * @returns List<Department>     Returns List of objects of type Department
	                          
     *Created By                   Siva Sai MOunika.Penneru
     *Created Date                 09-FEB-2023                           
	 
    **************************************************************************************/

    @Override
	public Department findDepartmentById(Integer departmentId) throws DepartmentIdNotFoundException {
    	Optional<Department> optDepartment = departmentRepository.findById(departmentId);
    	if(optDepartment == null) {
    		throw new DepartmentIdNotFoundException("Department ID not found");
    	}
    	return optDepartment.get();
	}
  
}
