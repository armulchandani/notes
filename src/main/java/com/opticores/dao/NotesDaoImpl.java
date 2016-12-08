package com.opticores.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.opticores.exception.NoEntityFoundException;
import com.opticores.model.Note;
import com.opticores.model.User;

@Repository
public class NotesDaoImpl extends HibernateDAO<Note> implements
		NotesDao {

	/** Default constructor
	 * 
	 */
	public NotesDaoImpl() {
		super(Note.class);
	}

	/** 
	 * {@inheritDoc}} 
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Note> getAllNotesForUser(User user) {
		
		Query query= getSession().getNamedQuery("fetchNotesByUserId");
		query.setParameter("user", user.getId());
		
		List<Note> notes= query.list();
		
		return notes;
	}
	
	
	/** 
	 * {@inheritDoc}} 
	 * 
	 */
	@Override
	public void addNotesForUser(Note note) {
		addEntity(note);
		
	}
	
	/** 
	 * {@inheritDoc}} 
	 * 
	 */
	@Override
	public void updateNote(Note note) {
		updateEntity(note);
		
	}
	
	/** 
	 * {@inheritDoc}} 
	 * @throws NoEntityFoundException 
	 * 
	 */
	@Override
	public void removeNote(Integer noteId) throws NoEntityFoundException {
		deleteEntityById(noteId);
		
	}

}
