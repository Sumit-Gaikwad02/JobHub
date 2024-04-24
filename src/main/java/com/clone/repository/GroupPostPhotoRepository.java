package com.clone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clone.model.GroupPostPhoto;
import com.clone.model.Groups;

@Repository
public interface GroupPostPhotoRepository extends JpaRepository<GroupPostPhoto, Long > {
List<GroupPostPhoto> findByGroup(Groups group);
}
