package com.clone.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.model.GroupRequests;
import com.clone.model.Groups;
import com.clone.model.Users;
import com.clone.repository.GroupRequestRepository;
import com.clone.repository.GroupsRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.GroupRequestService;
@Service
public class GroupRequestServiceImpl implements GroupRequestService {

	@Autowired
	private UsersRepository userRepo;

	@Autowired
	private GroupsRepository GroupRepo;

	@Autowired
	private GroupRequestRepository groupRequestRepo;

	@Override
	public String sendRequest(Long groupId, String email) {
		Groups group = GroupRepo.findById(groupId).orElseThrow(() -> new RuntimeException(" Group id not found"));
		Users user = userRepo.findByEmail(email);
		if (group != null && user != null) {
			GroupRequests request = new GroupRequests();
			request.setGroup(group);
			request.setSender(user);
			groupRequestRepo.save(request);
			return "request sent successfully.";
		} else {
			throw new RuntimeException("Group id or email not found");
		}
	}

	@Override
	public String acceptRequest(Long Id) {
		GroupRequests request = groupRequestRepo.findById(Id)
				.orElseThrow(() -> new RuntimeException("reuest id not found"));
		request.setStatus(true);
		groupRequestRepo.save(request);
		return "Request accepted successfully.";
	}

	@Override
	public String declineRequest(Long Id) {
		GroupRequests request = groupRequestRepo.findById(Id)
				.orElseThrow(() -> new RuntimeException("reuest id not found"));
		groupRequestRepo.deleteById(Id);
		return "Request declined successfully.";
	}

	@Override
	public List<GroupRequests> getGroupRequests(Long groupId) {
		Groups GroupId = GroupRepo.findById(groupId).orElseThrow(() -> new RuntimeException("group id not found"));
		boolean status = false;
		if (GroupId != null) {
			return groupRequestRepo.findByGroupAndStatus(GroupId, status);
		} else {
			throw new RuntimeException("group id not found");
		}
	}

	@Override
	public List<GroupRequests> getGroupMembers(Long groupId) {
		Groups GroupId = GroupRepo.findById(groupId).orElseThrow(() -> new RuntimeException("group id not found"));
		boolean status = true;
		if (GroupId != null) {
			return groupRequestRepo.findByGroupAndStatus(GroupId, status);
		} else {
			throw new RuntimeException("group id not found");
		}
	}

	@Override
	public List<GroupRequests> getSentGroupRequests(String email) {
		Users user = userRepo.findByEmail(email);
		if (user != null) {
			boolean status = false;
			return groupRequestRepo.findBySenderAndStatus(user, status);
		} else {
			throw new RuntimeException("group id not found");
		}

	}

}
