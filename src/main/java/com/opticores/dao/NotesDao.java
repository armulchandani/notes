package com.opticores.dao;

import java.util.List;

import com.opticores.model.Note;
import com.opticores.model.User;

public interface NotesDao {
	
	
	public List<Note> getAllNotesForUser(User user);

}
