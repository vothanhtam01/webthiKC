package com.example.demo.service;

import com.example.demo.entity.NotesEntity;
import com.example.demo.entity.UserEntity;

public interface NotesService {
	NotesEntity submit(NotesEntity notesEntity, UserEntity user);
}
