package com.clone.service;

import java.util.List;

import com.clone.model.GroupRequests;

public interface GroupRequestService {

    public String sendRequest(Long groupId,String email);
    
	public String acceptRequest(Long Id);
	
	public String declineRequest(Long Id);
	
	public List<GroupRequests> getGroupRequests(Long groupId);
	
	public List<GroupRequests> getGroupMembers(Long groupId);
	
	public List<GroupRequests> getSentGroupRequests(String email);
	
	
}
