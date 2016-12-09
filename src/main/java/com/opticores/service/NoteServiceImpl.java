package com.opticores.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opticores.dao.NotesDao;
import com.opticores.dao.UserDao;
import com.opticores.exception.NoEntityFoundException;
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
	public void updateNoteForUser(Note note,Integer userId) {
		// Set the notes update time
		note.setUpdated(new Timestamp(System.currentTimeMillis()));
		User user= userDao.fetchUser(userId);
		note.setUser(user);
		notesDao.updateNote(note);

	}
	
	/** 
	 * {@inheritDoc}
	 * @throws NoEntityFoundException 
	 */
	@Override
	public void removeNoteForUser(Integer noteId) throws NoEntityFoundException {
		notesDao.removeNote(noteId);

	}

	@Override
	public Note getNoteById(Integer id) {
		return notesDao.getNoteForId(id);
	}

}
