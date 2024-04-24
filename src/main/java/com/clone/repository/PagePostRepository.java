package com.clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clone.model.Page;
import com.clone.model.PagePost;

@Repository
public interface PagePostRepository extends JpaRepository<PagePost, Long> {

	List<PagePost> findByPage(Page page);
}
