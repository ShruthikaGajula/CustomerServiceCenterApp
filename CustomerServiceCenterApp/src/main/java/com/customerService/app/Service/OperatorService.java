package com.customerService.app.Service;

import java.util.List;

import com.customerService.app.Entity.Department;
import com.customerService.app.Entity.Operator;
import com.customerService.app.Exception.DepartmentIdNotFoundException;
import com.customerService.app.Exception.NoOperatorsListFoundException;
import com.customerService.app.Exception.OperatorAlreadyExistsException;
import com.customerService.app.Exception.OperatorException;
import com.customerService.app.Exception.OperatorIdNotFoundException;

public interface OperatorService {
	
	Operator addOperatorToDepartmentById(Operator newOperator, Integer departmentId) throws DepartmentIdNotFoundException;
	
	Operator getOperatorById(Integer operatorId) throws OperatorIdNotFoundException;
	
	List<Operator> getOperatorsByDepartmentId(Integer departmentId) throws DepartmentIdNotFoundException, NoOperatorsListFoundException;
	
	List<Operator> getAllOperators() throws NoOperatorsListFoundException;
	
	Operator updateOperatorById(Integer operatorId, Operator updatOperator) throws OperatorIdNotFoundException;
	
	Operator deleteOperatorFromDepartmentById(Integer departmentId, Integer operatorId) throws DepartmentIdNotFoundException, OperatorIdNotFoundException;


}
