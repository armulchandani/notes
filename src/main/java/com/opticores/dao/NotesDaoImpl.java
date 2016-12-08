package com.opticores.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.opticores.model.Note;
import com.opticores.model.User;

@Repository
public class NotesDaoImpl extends HibernateDAO<Note> implements
		NotesDao {

	public NotesDaoImpl() {
		super(Note.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Note> getAllNotesForUser(User user) {
		
		Query query= getSession().getNamedQuery("fetchNotesByUserId");
		query.setParameter("user", user.getId());
		
		List<Note> notes= query.list();
		
		return notes;
	}

	@Override
	public void addNotesForUser(Note note) {
		addEntity(note);
		
	}

}
