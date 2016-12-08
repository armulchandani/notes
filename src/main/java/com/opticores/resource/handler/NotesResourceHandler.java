package com.opticores.resource.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opticores.model.ErrorMessage;
import com.opticores.model.Note;
import com.opticores.service.NoteService;

/**
 * Main resource handler for handling clients requests for :
 * 
 * 1. fetch notes for a user 2. updating notes for a user 3. removing a user
 * note 4. adding a new note
 * 
 * 
 * @author anubhav
 *
 */

@RestController
@RequestMapping(value = "/user")
public class NotesResourceHandler {

	@Autowired
	private NoteService noteService;
	
	
	
	/**
	 * A function bound to an 'endpoint' to retrieve all the resources( NOTES ) for a given
	 * user
	 * 
	 * The 'endpoint' URI takes following form:
	 * 
	 * "/user/1/notes"
	 * 
	 * 
	 * @return a list of notes for a user
	 */
	@RequestMapping(value = "/{userid}/notes",produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.GET)
	public ResponseEntity<List<Note>> getAllNotes(@PathVariable Integer userid) {
		
		List<Note> notes= noteService.retrieveNotesForUser(userid);
		
		ResponseEntity<List<Note>> response= new ResponseEntity<List<Note>>(notes,HttpStatus.OK);
		
		return response;

	}
	
	@RequestMapping(value="/{userid}/notes",consumes=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.POST)
	public ResponseEntity<String> addNote(@RequestBody Note note,@PathVariable Integer userid) {
		
		noteService.addNoteForUser(note,userid);
		
		return new ResponseEntity<>("Note created successfully",HttpStatus.CREATED);
		
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleAllException(Exception ex) {
		
		ErrorMessage errorMessage= new ErrorMessage();
		errorMessage.setMessage("Some occured while performing operation");
		errorMessage.setDocumentLink("http://wwww.gotprint.com");
		
		return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
	
	
	

}
