package com.opticores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opticores.dao.NotesDao;
import com.opticores.dao.UserDao;
import com.opticores.model.Note;
import com.opticores.model.User;

/**
 * @author anubhav
 *
 */
@Service
@Transactional
public class NoteServiceImpl implements NoteService {
	
	
	@Autowired
	private NotesDao notesDao;
	
	@Autowired
	private UserDao userDao;

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public List<Note> retrieveNotesForUser(Integer userId) {
		
		User user= userDao.fetchUser(userId);
		return notesDao.getAllNotesForUser(user);
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void addNoteForUser(Note note,Integer userId) {
		
		User user= userDao.fetchUser(userId);
		note.setUser(user);
		
		notesDao.addNotesForUser(note);
		

	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void updateNoteForUser(Integer noteId) {
		// TODO Auto-generated method stub

	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void removeNoteForUser(Integer user) {
		// TODO Auto-generated method stub

	}

}
