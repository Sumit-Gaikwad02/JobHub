package com.clone.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.clone.model.Posts;
import com.clone.model.Users;
import com.clone.repository.PostsRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.PostsService;

@Service
public class PostsServiceImpl implements PostsService {

	@Autowired
	private UsersRepository userRepo;
	@Autowired
	private PostsRepository postRepository;

	@Override
	public Posts createPost(Posts post, String email, byte[] media) {

		Users user = userRepo.findByEmail(email);

		if (user != null) {
			post.setUser(user);
			System.out.println("userId added");
			post.setMedia(media);
			post.setTimeStamp(LocalDateTime.now());
			Posts savedPost = postRepository.save(post);
			return savedPost;

		} else {
			throw new UsernameNotFoundException("User id not found");
		}

	}

	@Override
	public List<Posts> getAllPosts(String email) {
		Users user = userRepo.findByEmail(email);

		if (user != null) {
			List<Posts> response = postRepository.findByUser(user);
			return response;
		} else {
			throw new UsernameNotFoundException("User not found");
		}

	}
}
