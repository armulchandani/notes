package com.opticores.resource.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opticores.config.CustomUserdetails;
import com.opticores.exception.AccessDeniedException;
import com.opticores.exception.InvalidRequestException;
import com.opticores.exception.NoEntityFoundException;
import com.opticores.exception.UserNotFoundException;
import com.opticores.model.Note;
import com.opticores.service.NoteService;
import com.opticores.service.UserService;
import static com.opticores.common.UriPathConstants.URI_PATH_API_NOTES;
import static com.opticores.common.UriPathConstants.URI_PATH_VARIABLE_NOTES_ID;

/**
 * Main resource handler, handling clients requests for :
 * 
 * 1. fetch notes for a user 2. updating notes for a user 3. removing a user
 * note 4. adding a new note
 * 
 * 
 * @author anubhav
 *
 */

@RestController
@RequestMapping(value = URI_PATH_API_NOTES)
public class NotesResourceHandler {

	private Logger LOGGER = LoggerFactory.getLogger(NotesResourceHandler.class);

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
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<List<Note>> getAllNotes() {

		final String METHOD_NAME = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		Integer userId = getUserId();

		LOGGER.info(
				"Entering method '{}' to retrieve all notes for user with id as '{}'",
				METHOD_NAME, userId);

		List<Note> notes = noteService.retrieveNotesForUser(userId);

		ResponseEntity<List<Note>> response = new ResponseEntity<List<Note>>(
				notes, HttpStatus.OK);

		LOGGER.info(
				"Exiting method '{}' to retrieve all notes for user with id as '{}'",
				METHOD_NAME, userId);

		return response;

	}

	/**
	 * A function bound to an 'endpoint' to add a resource( NOTES ) for a given
	 * user
	 * 
	 * The 'endpoint' URI takes following form:
	 * 
	 * "/api/notes"
	 * 
	 * Request METHOD TYPE: POST
	 * 
	 * 
	 * @return a list of notes for a user
	 */
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> addNote(@RequestBody Note note) {

		final String METHOD_NAME = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		Integer userId = getUserId();

		LOGGER.info(
				"Entering method '{}' to add a note for user with id as '{}'",
				METHOD_NAME, userId);

		noteService.addNoteForUser(note, userId);
		ResponseEntity<?> response = new ResponseEntity<>(
				"Note created successfully", HttpStatus.CREATED);

		LOGGER.info(
				"Exiting method '{}' to add a note for user with id as '{}'",
				METHOD_NAME, userId);

		return response;

	}

	/**
	 * A function bound to an 'endpoint' to add a resource( NOTES ) for a given
	 * user
	 * 
	 * The 'endpoint' URI takes following form:
	 * 
	 * "/api/notes"
	 * 
	 * Request METHOD TYPE: PUT
	 * 
	 * 
	 * @return a list of notes for a user
	 */
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<?> updateNote(@RequestBody Note note) {

		final String METHOD_NAME = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		Integer userId = getUserId();

		LOGGER.info(
				"Entering method '{}' to update a note for user with id as '{}'",
				METHOD_NAME, userId);

		Integer requestingUserId = getUserId();
		checkUserAuthorization(note.getId(), requestingUserId);

		noteService.updateNoteForUser(note, requestingUserId);

		LOGGER.info(
				"Exiting method '{}' to update a note for user with id as '{}'",
				METHOD_NAME, userId);

		return new ResponseEntity<>("Note updated successfully", HttpStatus.OK);

	}

	/**
	 * A function bound to an 'endpoint' to remove a resource( NOTES ) for a
	 * given user
	 * 
	 * The 'endpoint' URI takes following form:
	 * 
	 * "/api/notes/2"
	 * 
	 * Request METHOD TYPE: DELETE
	 * 
	 * 
	 * @return a list of notes for a user
	 */
	@RequestMapping(value = URI_PATH_VARIABLE_NOTES_ID, method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteNote(@PathVariable Integer noteid)
			throws NoEntityFoundException {

		final String METHOD_NAME = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		Integer userId = getUserId();

		LOGGER.info(
				"Entering method '{}' to delete a note for user with id as '{}'",
				METHOD_NAME, userId);

		// 1. Retrieve the user id of the logged in user
		Integer requestingUserId = getUserId();

		// 2. Check user access to perform this operation
		checkUserAuthorization(noteid, requestingUserId);

		noteService.removeNoteForUser(noteid);

		LOGGER.info(
				"Exiting method '{}' to delete a note for user with id as '{}'",
				METHOD_NAME, userId);

		return new ResponseEntity<>("Note deleted successfully", HttpStatus.OK);

	}

	/**
	 * A function to retrieve a user id from security context post user
	 * authentication
	 * 
	 * @return <userid> of an authenticated user
	 */
	private Integer getUserId() {

		CustomUserdetails userDetails = (CustomUserdetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		Integer authenticatedUserId = userDetails.getUserId();

		if (null == authenticatedUserId) {
			throw new UserNotFoundException();
		}

		return authenticatedUserId;
	}

	/**
	 * This function basically checks if the requesting user is authorized to
	 * perform an operation like UPDATE/DELETE a resource (NOTE)
	 * 
	 * if not authorized, it throws an appropriate runtime exception
	 * 
	 * @param note
	 * @param requestingUserId
	 * 
	 */
	private void checkUserAuthorization(Integer noteId, Integer requestingUserId) {

		if (null == noteId) {
			throw new InvalidRequestException(
					"Note id is missing. cannot proceed with request. Please correct and try again");
		}

		Note originalNote = noteService.getNoteById(noteId);

		if (!(originalNote.getUser().getId().equals(requestingUserId))) {
			throw new AccessDeniedException();
		}

	}

}
