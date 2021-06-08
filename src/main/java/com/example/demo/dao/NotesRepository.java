package com.example.demo.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.NotesEntity;
import com.example.demo.entity.UserEntity;

@Repository
public interface NotesRepository extends JpaRepository<NotesEntity, Long> {
	List<NotesEntity> findByTitleContaining(String title);
	List<NotesEntity> findByUser(UserEntity user);
	
}