package com.clone.service;

import java.util.List;
import com.clone.model.PostEvent;

public interface PostEventService {

	PostEvent createEvent(PostEvent event,String email);

	List<PostEvent> fetchEvent(String email);
	
	String deleteEvent(Long eventId);
	
}
