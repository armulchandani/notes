package com.opticores.base;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opticores.model.Note;
import com.opticores.service.NoteService;

public class NodesTest extends BaseTest {

	@Autowired
	private NoteService noteService;

	@Test
	public void retrieveAllNotesForUserShouldPass() {

		List<Note> notes = noteService.retrieveNotesForUser(1);

		assertNotNull(notes);
	}

}
