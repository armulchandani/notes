package com.opticores.service;

import java.util.List;

import com.opticores.exception.NoEntityFoundException;
import com.opticores.model.Note;

/**
 * An interface specifying a 'contract' or 'what' a user of the note application
 * could perform
 * 
 * There is only a single implementation {@link NoteServiceImpl}
 * 
 * @author anubhav
 *
 */
public interface NoteService {

	/**
	 * This function is basically responsible for retrieving all the notes for a
	 * user provided its user id
	 * 
	 * 
	 * @param user
	 * @return a list of all the notes for a particular user
	 */
	public List<Note> retrieveNotesForUser(Integer user);

	/**
	 * This function adds a new note for a user provided its user id
	 * @param note 
	 * 
	 * 
	 * @param user
	 */
	public void addNoteForUser(Note note, Integer user);

	/**
	 * This function updates an existing note for a user provided its note id
	 * @param userid 
	 * 
	 * 
	 * @param an instanceof note object which needs to be updated
	 */

	public void updateNoteForUser(Note note, Integer userid);

	/**
	 * This function removes a given note for a particular user provided its
	 * note id
	 * 
	 * @param note id for removing 
	 * @throws NoEntityFoundException 
	 */

	public void removeNoteForUser(Integer noteId) throws NoEntityFoundException;
	
	
	/**
	 * This function retrieves a given note for the given note id
	 * 
	 * @param note id for retrieving 
	 */
	public Note getNoteById(Integer noteid);

}
