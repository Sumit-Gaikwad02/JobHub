package com.clone.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.exceptionHandler.EmailNotFoundException;
import com.clone.model.GroupPostPhoto;
import com.clone.model.Groups;
import com.clone.model.Users;
import com.clone.repository.GroupPostPhotoRepository;
import com.clone.repository.GroupsRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.GroupPostPhotoService;

@Service
public class GroupPostPhotoImpl implements GroupPostPhotoService {

	@Autowired
	private UsersRepository userRepo;

	@Autowired
	private GroupsRepository groupRepo;

	@Autowired
	private GroupPostPhotoRepository Repo;

	@Override
	public String SavePhoto(byte[] media, Long GroupId, String email) {
		Users user=userRepo.findByEmail(email);
		Groups groupId=groupRepo.findById(GroupId).orElseThrow(()->new RuntimeException("GroupId not found."));
		if(user!=null && groupId!=null ) {
			GroupPostPhoto data=new GroupPostPhoto();
			data.setPhoto(media);
			data.setGroup(groupId);
			data.setUser(user);
			Repo.save(data);
			return "phtot saved successfully.";
		}else {
			throw new EmailNotFoundException("email not found.");
		}
		
	}

	@Override
	public List<GroupPostPhoto> getPostedPhotos(Long groupId) {
		Groups GroupId=groupRepo.findById(groupId).orElseThrow(()->new RuntimeException("GroupId not found."));
		return Repo.findByGroup(GroupId) ;
	}

}
