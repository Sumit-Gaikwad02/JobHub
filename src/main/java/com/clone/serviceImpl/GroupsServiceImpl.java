package com.clone.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.model.Groups;
import com.clone.model.Users;
import com.clone.repository.GroupsRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.GroupsService;

@Service
public class GroupsServiceImpl implements GroupsService {

	@Autowired
	private UsersRepository UserRepo;

	@Autowired
	private GroupsRepository groupRepo;

	@Override
	public Groups createGroups(Groups groups, String email) {
		Users user = UserRepo.findByEmail(email);

		if (user != null) {
			groups.setUser(user);
			return groupRepo.save(groups);
		} else {
			throw new EmailNotFoundException("registered Email not found");
		}
	}

	@Override
	public List<Groups> getByAdmin(String email) {
		Users user = UserRepo.findByEmail(email);
		if (user != null) {
			return groupRepo.findByUser(user);
		} else {
			throw new EmailNotFoundException("registered Email not found");
		}
	}

	@Override
	public Groups getById(Long groupId) {
		Groups group = groupRepo.findById(groupId).orElseThrow(() -> new RuntimeException("GroupId not found."));
		group.setGroupId(group.getGroupId());
		group.setCoverImage(group.getCoverImage());
		group.setProfilePicture(group.getProfilePicture());
		group.setGroupName(group.getGroupName());
		group.setDescription(group.getDescription());
		group.setIndustryType(group.getIndustryType());
		group.setLocation(group.getLocation());
		return group;

	}

	@Override
	public String updateCoverImage(byte[] image, Long groupId) {
		Groups id = groupRepo.findById(groupId).orElseThrow(() -> new RuntimeException("Group id not found"));
		id.setCoverImage(image);
		groupRepo.save(id);
		return "cover Photo uspdated successfully.";
	}

	@Override
	public String updateProfilePicture(byte[] image, Long groupId) {
		Groups id = groupRepo.findById(groupId).orElseThrow(() -> new RuntimeException("Group id not found"));
		id.setProfilePicture(image);
		groupRepo.save(id);
		return "profile Photo uspdated successfully.";
	}

}
