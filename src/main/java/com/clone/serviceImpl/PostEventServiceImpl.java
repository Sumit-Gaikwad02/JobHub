package com.clone.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clone.exceptionHandler.EmptyInputException;
import com.clone.model.PostEvent;
import com.clone.model.Users;
import com.clone.repository.PostEventRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.PostEventService;

/** Author:Sumit *****/
@Service
public class PostEventServiceImpl implements PostEventService {

	@Autowired
	private PostEventRepository repo;

	@Autowired
	private UsersRepository userRepo;

	// save post with user id
	@Override
	public PostEvent createEvent(PostEvent event, String email) {
		Users user = userRepo.findByEmail(email);
		if (user != null) {
			event.setUser(user);
			return repo.save(event);
		} else {
			throw new EmptyInputException("PostId found null");
		}
	}

	// get list of post with by user id
	@Override
	public List<PostEvent> fetchEvent(String email) {
		Users user = userRepo.findByEmail(email);
		if (user != null) {
			return repo.findByUser(user);
		} else {
			throw new UsernameNotFoundException("username not found");
		}

	}

	@Override
	public String deleteEvent(Long eventId) {

		PostEvent EventId = repo.findById(eventId).orElseThrow(() -> new RuntimeException("Post not found."));
		if (EventId != null) {
			repo.deleteById(eventId);
			return "Event deleted successfully.";
		} else {
			throw new RuntimeException("Post not found.");
		}

	}
}
