package com.opticores.service;

import java.util.List;

import com.opticores.exception.NoEntityFoundException;
import com.opticores.model.Article;

/**
 * An interface specifying a 'contract' or 'what' a user of the article application
 * could perform
 * 
 * There is only a single implementation {@link ArticleServiceImpl}
 * 
 * @author anubhav
 *
 */
public interface ArticleService {

	/**
	 * This function is basically responsible for retrieving all the articles for a
	 * user provided its user id
	 * 
	 * 
	 * @param user
	 * @return a list of all the articles for a particular user
	 */
	public List<Article> retrieveArticlesForUser(Integer user);

	/**
	 * This function adds a new article for a user provided its user id
	 * @param article 
	 * 
	 * 
	 * @param user
	 */
	public void addArticleForUser(Article article, Integer user);

	/**
	 * This function updates an existing article for a user provided its article id
	 * and user id 
	 * 
	 * 
	 * @param an instance of article object which needs to be updated
	 * @param userid
	 */

	public void updateArticleForUser(Article article, Integer userid);

	/**
	 * This function removes a given article for a particular user provided its
	 * article id
	 * 
	 * @param article id for removing 
	 * 
	 * @throws NoEntityFoundException 
	 */

	public void removeArticleForUser(Integer articleId) throws NoEntityFoundException;
	
	
	/**
	 * This function retrieves a given article for the given article id
	 * 
	 * @param article id for retrieving 
	 */
	public Article getArticleById(Integer articleId);

}
