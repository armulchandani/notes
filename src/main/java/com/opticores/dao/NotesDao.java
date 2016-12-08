package com.opticores.dao;

import java.util.List;

import com.opticores.exception.NoEntityFoundException;
import com.opticores.model.Note;
import com.opticores.model.User;

/**
 * Simple interface of DAO layer reflecting what needs to be done.
 * 
 * Concrete implementation {@link NotesDaoImpl}
 * 
 * @author anubhav
 *
 */
public interface NotesDao {

	/**
	 * This function is basically responsible for retrieving all the notes for a
	 * particular user
	 * 
	 * @param user
	 * @return a list of notes
	 */
	public List<Note> getAllNotesForUser(User user);

	/** This function add a new note for the user
	 * 
	 * 
	 * @param note
	 */
	public void addNotesForUser(Note note);
	
	
	/** This function updates an existing note for the user
	 * 
	 * 
	 * @param note
	 */
	public void updateNote(Note note);
	
	
	
	/** This function removes an existing note for the user
	 * 
	 * 
	 * @param noteId
	 * @return 
	 * @throws NoEntityFoundException 
	 */
	public void removeNote(Integer noteId) throws NoEntityFoundException;

}
