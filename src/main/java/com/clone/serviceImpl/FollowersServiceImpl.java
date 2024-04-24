package com.clone.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clone.model.Followers;
import com.clone.model.Users;
import com.clone.repository.FollowerRepository;
import com.clone.repository.UsersRepository;
import com.clone.service.FollowersService;

@Service
public class FollowersServiceImpl implements FollowersService {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private FollowerRepository followersRepository;

	@Autowired
	private NotificationsServiceImpl notificationService;

	@Override
	public Followers followUser(Users user, String email) {
		Users follower = userRepository.findByEmail(email);
		Users followingUser = userRepository.findById(user.getUserId())
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));

		if (followingUser != null && follower != null) {
			Followers follow = new Followers();
			follow.setFollower(follower);
			follow.setFollowingUser(followingUser);

//	 follow notification for user
			String message = follower.getFirstName() + follower.getLastName() + "satrted following you.";
			notificationService.saveNotification(message, followingUser);
			return followersRepository.save(follow);
		} else {
			throw new UsernameNotFoundException("Sender or receiver not found.");
		}
	}

	@Override
	public List<Followers> getFollowers(String email) {
		Users user = userRepository.findByEmail(email);
		if (user != null) {
			return followersRepository.findByfollowingUser(user);
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}

	@Override
	public List<Followers> getFollowing(String email) {
		Users follower = userRepository.findByEmail(email);
		if (follower != null) {
			return followersRepository.findByfollower(follower);
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}

	@Override
	public String unfollow(long followId) {
		followersRepository.deleteById(followId);
		return "Unfollowed successfully.";
	}
}