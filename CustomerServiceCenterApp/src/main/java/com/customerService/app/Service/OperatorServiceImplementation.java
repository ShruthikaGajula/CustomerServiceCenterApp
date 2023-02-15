package com.customerService.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerService.app.Entity.Department;
import com.customerService.app.Entity.Operator;
import com.customerService.app.Exception.DepartmentIdNotFoundException;
import com.customerService.app.Exception.IssueException;
import com.customerService.app.Exception.NoOperatorsListFoundException;
import com.customerService.app.Exception.OperatorIdNotFoundException;
import com.customerService.app.Repository.DepartmentRepository;
import com.customerService.app.Repository.OperatorRepository;

/************************************************************************************
 * @author        Likhitha Geddam
 
 * Description    It is a Service class that provides the service methods for adding a new Operator, 
                  Updating existing Operator details ,Fetching Details of a Operator by operator Id,
                  Fetching all Operators List, Assigning Operator to Departments By Id.
                  
 *Version         1.0 
                
 *Created Date    09-FEB-2023
 ************************************************************************************/
@Service
public class OperatorServiceImplementation implements OperatorService{
	@Autowired
	private OperatorRepository operatorRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	/************************************************************************************
	 * Method: 			           addOperatorToDepartmentById
	 
     * Description: 			   To add the new Operator Details to Department Id
     
	 * @param addOperatorToDepartmentById:
	 		               		   Operator object and department id of type Integer are passed
	 
	 * @returns Operator:          Returns object of Operator if successfully details Are added otherwise throws Exception
	                               
	 * @throws DepartmentIdNotFoundException:     
	 							   It is raised if Department Id is not found in the Database
	                               
     * Created By                  Likhitha Geddam
     
     * Created Date                09-FEB-2023                           
    ************************************************************************************/
	@Override
	public Operator addOperatorToDepartmentById(Operator newOperator,Integer departmentId) throws DepartmentIdNotFoundException {
		Optional<Department> optDeptartment = this.departmentRepository.findById(departmentId);
		if(optDeptartment.isEmpty()) {
			throw new DepartmentIdNotFoundException("!!! Department Id "+departmentId+" Does Not Exist !!!");
		}
		Department foundDepartment = optDeptartment.get();
		Operator addOperator = this.operatorRepository.save(newOperator);
		foundDepartment.getOperatorList().add(newOperator);
		this.departmentRepository.save(foundDepartment);
		return addOperator;
	}

	/************************************************************************************
	 * Method: 			           getOperatorById
	 
     * Description: 			   To retrive Operator by operator Id
     
	 * @param getOperatorById:     Operator id of type Integer is passed
	 
	 * @returns Operator:          Returns object of Operator if successfully details are fetched otherwise throws Exception
	                               
	 * @throws OperatorIdNotFoundException:     
	 							   It is raised if Operator Id is not found in the Database
	                               
     * Created By                  Likhitha Geddam
     
     * Created Date                09-FEB-2023                           
    ************************************************************************************/
	@Override
	public Operator getOperatorById(Integer operatorId) throws OperatorIdNotFoundException {

		Optional<Operator> optOperator = operatorRepository.findById(operatorId);
		if(optOperator.isEmpty()) {
			throw new OperatorIdNotFoundException("!!! Operator ID Not Found !!!");
		}
		return optOperator.get();
	}
	
	/************************************************************************************
	 * Method: 			           getOperatorsByDepartmentId
	 
     * Description: 			   To retrive List of Operators by operator Department Id
     
	 * @param getOperatorsByDepartmentId:     
	 							   department id of type Integer is passed
	 
	 * @returns Operator:          Returns object of Operator if successfully details are fetched otherwise throws Exception
	                               
	 * @throws DepartmentIdNotFoundException, NoOperatorsListFoundException:     
	 							   It is raised if Operator List and department id is not found in the Database
	                               
     * Created By                  Likhitha Geddam
     
     * Created Date                09-FEB-2023                           
    ************************************************************************************/
	
	@Override
	public List<Operator> getOperatorsByDepartmentId(Integer departmentId) throws DepartmentIdNotFoundException, NoOperatorsListFoundException {
		Optional<Department> optDepartment = this.departmentRepository.findById(departmentId);
		if(optDepartment.isEmpty()) {
			throw new DepartmentIdNotFoundException("Department Id does not Exist");
		}
		Department opt = optDepartment.get();
		if(opt.getOperatorList().isEmpty()) {
			throw new NoOperatorsListFoundException("!!! No Operators Found in the List !!!");
		}
		return opt.getOperatorList();
	}
	
	/************************************************************************************
	 * Method: 			           getAllOperators
	 
     * Description: 			   To retrive List of Operators by operator Department Id
     
	 * @param getAllOperators:     department id of type Integer is passed
	 
	 * @returns Operator:          Returns object of Operator if successfully details are fetched otherwise throws Exception
	                               
	 * @throws NoOperatorsListFoundException:     
	 							   It is raised if Operator Id is not found in the Database
	                               
     * Created By                  Likhitha Geddam
     
     * Created Date                09-FEB-2023                           
    ************************************************************************************/
	@Override
	public List<Operator> getAllOperators() throws NoOperatorsListFoundException {
		List<Operator> operatorsList = this.operatorRepository.findAll();
		if(operatorsList.isEmpty()) {
			throw new NoOperatorsListFoundException("!!! No Operators Found in the List !!!");
		}
		return this.operatorRepository.findAll();
	}
	
	/************************************************************************************
	 * Method: 			           updateOperatorById
	 
     * Description: 			   To update the existing Operator Details
     
	 * @param updateOperatorById:  Operator object and department id of type Integer are passed
	 
	 * @returns Operator:          Returns object of Operator if successfully details are updated otherwise throws Exception
	                               
	 * @throws OperatorIdNotFoundException:     
	 							   It is raised if Operator Id is not found in the Database
	                               
     * Created By                  Likhitha Geddam
     
     * Created Date                09-FEB-2023                           
    ************************************************************************************/
	@Override
	public Operator updateOperatorById(Integer operatorId, Operator updateOperator) throws OperatorIdNotFoundException {
		Optional<Operator> findOperator = operatorRepository.findById(operatorId);
		if(findOperator.isEmpty()) {
			throw new OperatorIdNotFoundException("No Department Found for Id :"+operatorId);
		}
		Operator addOperator = findOperator.get();
		addOperator.setOperatorId(updateOperator.getOperatorId());
		addOperator.setFirstName(updateOperator.getFirstName());
		addOperator.setLastName(updateOperator.getLastName());
		addOperator.setEmail(updateOperator.getEmail());
		addOperator.setMobile(updateOperator.getMobile());
		addOperator.setCity(updateOperator.getCity());
		return operatorRepository.save(addOperator);
	}

	/************************************************************************************
	 * Method: 			           deleteOperatorFromDepartmentById
	 
     * Description: 			   To update the existing Operator Details
     
	 * @param deleteOperatorFromDepartmentById:  
	 							   Operator id  and department id of type Integer are passed
	 
	 * @returns Operator:          Returns object of Operator if successfully details are deleted otherwise throws Exception
	                               
	 * @throws OperatorIdNotFoundException:     
	 							   It is raised if Operator Id is not found in the Database
	                               
     * Created By                  Likhitha Geddam
     
     * Created Date                09-FEB-2023                           
    ************************************************************************************/
	@Override
	public Operator deleteOperatorFromDepartmentById(Integer departmentId, Integer operatorId) throws DepartmentIdNotFoundException, OperatorIdNotFoundException {
		Optional<Department> optDepartment = this.departmentRepository.findById(departmentId);
		if(optDepartment.isEmpty()) {
			throw new DepartmentIdNotFoundException("!!! Department Id "+departmentId+" Not Found !!!");
		}
		Department foundDepartment = optDepartment.get();
		Optional<Operator> optOperator = this.operatorRepository.findById(operatorId);
		if(optOperator.isEmpty()) {
			throw new OperatorIdNotFoundException("!!! Operator Id "+departmentId+" Not Found !!!");
		}
		Operator foundOperator = optOperator.get();
		foundDepartment.getOperatorList().remove(foundOperator);
		this.departmentRepository.save(foundDepartment);
		this.operatorRepository.delete(foundOperator);
		return foundOperator;
	}
	
	
}
