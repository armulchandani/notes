package com.opticores.dao;

import java.util.List;

import com.opticores.exception.NoEntityFoundException;
import com.opticores.model.Article;
import com.opticores.model.User;

/**
 * Simple interface of DAO layer reflecting what needs to be done.
 * 
 * Concrete implementation {@link ArticleDaoImpl}
 * 
 * @author anubhav
 *
 */
public interface ArticleDao {

	/**
	 * This function is basically responsible for retrieving all the articles for a
	 * particular user
	 * 
	 * @param user
	 * @return a list of articles
	 */
	public List<Article> getAllArticlesForUser(User user);

	/** This function add a new article for the user
	 * 
	 * 
	 * @param article
	 */
	public void addArticlesForUser(Article article);
	
	
	/** This function updates an existing article for the user
	 * 
	 * 
	 * @param article
	 */
	public void updateArticle(Article article);
	
	
	
	/** This function removes an existing article for the user
	 * 
	 * 
	 * @param articleId
	 * @return 
	 * @throws NoEntityFoundException 
	 */
	public void removeArticle(Integer articleId) throws NoEntityFoundException;

	public Article getArticleForId(Integer id);

}
