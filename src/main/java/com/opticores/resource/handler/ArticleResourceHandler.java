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
import com.opticores.model.Article;
import com.opticores.service.ArticleService;
import com.opticores.service.UserService;
import static com.opticores.common.UriPathConstants.URI_PATH_API_ARTICLES;
import static com.opticores.common.UriPathConstants.URI_PATH_VARIABLE_ARTICLES_ID;

/**
 * Main resource handler, handling clients requests for :
 * 
 * 1. fetch articles for a user 
 * 2. updating articles for a user 
 * 3. removing a user article
 * 4. adding a new article
 * 
 * 
 * @author anubhav
 *
 */

@RestController
@RequestMapping(value = URI_PATH_API_ARTICLES)
public class ArticleResourceHandler {

	private Logger LOGGER = LoggerFactory.getLogger(ArticleResourceHandler.class);

	@Autowired
	private ArticleService articleService;

	@Autowired
	private UserService userService;

	/**
	 * A function bound to an 'endpoint' to retrieve all the resources( ARTICLES )
	 * for a given user
	 * 
	 * The 'endpoint' URI takes following form:
	 * 
	 * "/user/1/articles"
	 * 
	 * Request METHOD TYPE: GET
	 * 
	 * 
	 * @return a list of articles for a user
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<List<Article>> getAllArticles() {

		final String METHOD_NAME = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		Integer userId = getUserId();

		LOGGER.info(
				"Entering method '{}' to retrieve all articles for user with id as '{}'",
				METHOD_NAME, userId);

		List<Article> articles = articleService.retrieveArticlesForUser(userId);

		ResponseEntity<List<Article>> response = new ResponseEntity<List<Article>>(
				articles, HttpStatus.OK);

		LOGGER.info(
				"Exiting method '{}' to retrieve all articles for user with id as '{}'",
				METHOD_NAME, userId);

		return response;

	}

	/**
	 * A function bound to an 'endpoint' to add a resource( ARTICLES ) for a given
	 * user
	 * 
	 * The 'endpoint' URI takes following form:
	 * 
	 * "/api/articles"
	 * 
	 * Request METHOD TYPE: POST
	 * 
	 * 
	 * @return a list of articles for a user
	 */
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> addArticle(@RequestBody Article article) {

		final String METHOD_NAME = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		Integer userId = getUserId();

		LOGGER.info(
				"Entering method '{}' to add a article for user with id as '{}'",
				METHOD_NAME, userId);

		articleService.addArticleForUser(article, userId);
		ResponseEntity<?> response = new ResponseEntity<>(
				"Article created successfully", HttpStatus.CREATED);

		LOGGER.info(
				"Exiting method '{}' to add a article for user with id as '{}'",
				METHOD_NAME, userId);

		return response;

	}

	/**
	 * A function bound to an 'endpoint' to add a resource( ARTICLES ) for a given
	 * user
	 * 
	 * The 'endpoint' URI takes following form:
	 * 
	 * "/api/articles"
	 * 
	 * Request METHOD TYPE: PUT
	 * 
	 * 
	 * @return a list of articles for a user
	 */
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<?> updateArticle(@RequestBody Article article) {

		final String METHOD_NAME = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		Integer userId = getUserId();

		LOGGER.info(
				"Entering method '{}' to update a article for user with id as '{}'",
				METHOD_NAME, userId);

		Integer requestingUserId = getUserId();
		checkUserAuthorization(article.getId(), requestingUserId);

		articleService.updateArticleForUser(article, requestingUserId);

		LOGGER.info(
				"Exiting method '{}' to update a article for user with id as '{}'",
				METHOD_NAME, userId);

		return new ResponseEntity<>("Article updated successfully", HttpStatus.OK);

	}

	/**
	 * A function bound to an 'endpoint' to remove a resource( ARTICLES ) for a
	 * given user
	 * 
	 * The 'endpoint' URI takes following form:
	 * 
	 * "/api/articles/2"
	 * 
	 * Request METHOD TYPE: DELETE
	 * 
	 * 
	 * @return a list of articles for a user
	 */
	@RequestMapping(value = URI_PATH_VARIABLE_ARTICLES_ID, method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteArticle(@PathVariable Integer articleid)
			throws NoEntityFoundException {

		final String METHOD_NAME = Thread.currentThread().getStackTrace()[1]
				.getMethodName();

		Integer userId = getUserId();

		LOGGER.info(
				"Entering method '{}' to delete a article for user with id as '{}'",
				METHOD_NAME, userId);

		// 1. Retrieve the user id of the logged in user
		Integer requestingUserId = getUserId();

		// 2. Check user access to perform this operation
		checkUserAuthorization(articleid, requestingUserId);

		articleService.removeArticleForUser(articleid);

		LOGGER.info(
				"Exiting method '{}' to delete a article for user with id as '{}'",
				METHOD_NAME, userId);

		return new ResponseEntity<>("Article deleted successfully", HttpStatus.OK);

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
	 * perform an operation like UPDATE/DELETE a resource (ARTICLE)
	 * 
	 * if not authorized, it throws an appropriate runtime exception
	 * 
	 * @param articleId
	 * @param requestingUserId
	 * 
	 */
	private void checkUserAuthorization(Integer articleId, Integer requestingUserId) {

		if (null == articleId) {
			throw new InvalidRequestException(
					"Article id is missing. cannot proceed with request. Please correct and try again");
		}

		Article originalArticle = articleService.getArticleById(articleId);

		if (!(originalArticle.getUser().getId().equals(requestingUserId))) {
			throw new AccessDeniedException();
		}

	}

}
