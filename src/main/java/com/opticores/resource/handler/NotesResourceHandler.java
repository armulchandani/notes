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

import com.opticores.exception.NoEntityFoundException;
import com.opticores.exception.UserNotFoundException;
import com.opticores.model.ErrorMessage;
import com.opticores.model.Note;
import com.opticores.model.User;
import com.opticores.service.NoteService;
import com.opticores.service.UserService;

/**
 * Main resource handler, handling clients requests for :
 * 
 * 1. fetch notes for a user 
 * 2. updating notes for a user 
 * 3. removing a user note 
 * 4. adding a new note
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

	@Autowired
	private UserService userService;

	/**
	 * A function bound to an 'endpoint' to retrieve all the resources( NOTES )
	 * for a given user
	 * 
	 * The 'endpoint' URI takes following form:
	 * 
	 * "/user/1/notes"
	 * 
	 * Request METHOD TYPE: GET
	 * 
	 * 
	 * @return a list of notes for a user
	 */
	@RequestMapping(value = "/{userid}/notes", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<List<Note>> getAllNotes(@PathVariable Integer userid) {

		// 1. Check user existence
		checkUserExistence(userid);

		List<Note> notes = noteService.retrieveNotesForUser(userid);

		ResponseEntity<List<Note>> response = new ResponseEntity<List<Note>>(
				notes, HttpStatus.OK);

		return response;

	}

	/**
	 * A function bound to an 'endpoint' to add a resource( NOTES ) for a given
	 * user
	 * 
	 * The 'endpoint' URI takes following form:
	 * 
	 * "/user/1/notes"
	 * 
	 * Request METHOD TYPE: POST
	 * 
	 * 
	 * @return a list of notes for a user
	 * @throws UserNotFoundException
	 */
	@RequestMapping(value = "/{userid}/notes", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> addNote(@RequestBody Note note,
			@PathVariable Integer userid) {
		
		// 1. Check user existence
		checkUserExistence(userid);
		noteService.addNoteForUser(note, userid);
		ResponseEntity<?> response= new ResponseEntity<>("Note created successfully",
				HttpStatus.CREATED);

		return response;

	}

	private void checkUserExistence(Integer userid)
			throws UserNotFoundException {
		User user = userService.getUserById(userid);
		if (null == user) {
			throw new UserNotFoundException();
		}
	}

	/**
	 * A function bound to an 'endpoint' to add a resource( NOTES ) for a given
	 * user
	 * 
	 * The 'endpoint' URI takes following form:
	 * 
	 * "/user/1/notes"
	 * 
	 * Request METHOD TYPE: PUT
	 * 
	 * 
	 * @return a list of notes for a user
	 */
	@RequestMapping(value = "/{userid}/notes", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<?> updateNote(@RequestBody Note note,
			@PathVariable Integer userid) {

		// 1. Check user existence
		checkUserExistence(userid);

		noteService.updateNoteForUser(note, userid);

		return new ResponseEntity<>("Note updated successfully", HttpStatus.OK);

	}

	/**
	 * A function bound to an 'endpoint' to remove a resource( NOTES ) for a
	 * given user
	 * 
	 * The 'endpoint' URI takes following form:
	 * 
	 * "/user/notes/2"
	 * 
	 * Request METHOD TYPE: DELETE
	 * 
	 * 
	 * @return a list of notes for a user
	 */
	@RequestMapping(value = "/notes/{noteid}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteNote(@PathVariable Integer noteid)
			throws NoEntityFoundException {

		noteService.removeNoteForUser(noteid);

		return new ResponseEntity<>("Note deleted successfully", HttpStatus.OK);

	}

	/**
	 * A handler to catch if a user is not found and an exception and send
	 * appropriate response to the caller/client
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleUserNotFound(
			UserNotFoundException ex) {

		ErrorMessage errorMessage = new ErrorMessage();
		StringBuilder builder = new StringBuilder("User not found: ");
		errorMessage.setMessage(builder.toString());
		errorMessage.setDocumentLink("http://wwww.gotprint.com");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

	}

	/**
	 * A handler to catch all exception and send appropriate response to the
	 * caller/client
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleAllException(Exception ex) {

		ErrorMessage errorMessage = new ErrorMessage();
		StringBuilder builder = new StringBuilder(
				"Some occured while performing operation : ");
		builder.append(ex.getMessage());
		errorMessage.setMessage(builder.toString());
		errorMessage.setDocumentLink("http://wwww.gotprint.com");

		return new ResponseEntity<>(errorMessage,
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

}