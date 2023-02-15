package com.customerService.app.Service;

import java.util.List;

import com.customerService.app.Entity.Call;
import com.customerService.app.Entity.Issue;
import com.customerService.app.Exception.CallIdNotFoundException;
import com.customerService.app.Exception.CallListNotFoundException;
import com.customerService.app.Exception.CustomerException;

public interface CallService {
	
	Call addCallDetailsToCustomerTable(Call newCall, Integer customerId)throws CustomerException;
	
	Issue assignIssueToCall(Issue newIssue, Integer callId)throws CallIdNotFoundException;
	
	Call getCallById(Integer callId)throws CallIdNotFoundException;
	
	List<Call> getCallListByCustomerId(Integer customerId) throws CustomerException, CallListNotFoundException;
	
	List<Call> getAllCalls();
	
	Issue getIssueByCallId(Integer callId)throws CallIdNotFoundException;
	
	Call deleteCallByCustomerId(Integer indexId, Integer cutsomerId)throws CustomerException, CallListNotFoundException;
	
	List<Call> deleteCallListByCustomerId(Integer customerId)throws CustomerException, CallListNotFoundException;

}
