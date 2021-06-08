package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.NotesRepository;
import com.example.demo.entity.NotesEntity;
import com.example.demo.entity.UserEntity;

@Service	
public class NotesSeviceImpl implements NotesService {

	@Autowired
	NotesRepository noteRepo;
	
	@Override
	public NotesEntity submit(NotesEntity notesEntity, UserEntity user) {
		notesEntity.setDateCreated(LocalDateTime.now());
		notesEntity.setUser(user);
		noteRepo.save(notesEntity);
		return notesEntity;
	}

}
