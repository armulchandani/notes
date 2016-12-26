package com.opticores.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opticores.dao.ArticleDao;
import com.opticores.dao.UserDao;
import com.opticores.exception.NoEntityFoundException;
import com.opticores.model.Article;
import com.opticores.model.User;

/**
 * @author anubhav
 *
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
	
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private UserDao userDao;

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public List<Article> retrieveArticlesForUser(Integer userId) {
		
		User user= userDao.fetchUser(userId);
		return articleDao.getAllArticlesForUser(user);
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void addArticleForUser(Article article,Integer userId) {
		
		User user= userDao.fetchUser(userId);
		article.setUser(user);
		
		articleDao.addArticlesForUser(article);
		

	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void updateArticleForUser(Article article,Integer userId) {
		// Set the articles update time
		article.setUpdated(new Timestamp(System.currentTimeMillis()));
		User user= userDao.fetchUser(userId);
		article.setUser(user);
		articleDao.updateArticle(article);

	}
	
	/** 
	 * {@inheritDoc}
	 * @throws NoEntityFoundException 
	 */
	@Override
	public void removeArticleForUser(Integer articleId) throws NoEntityFoundException {
		articleDao.removeArticle(articleId);

	}

	@Override
	public Article getArticleById(Integer id) {
		return articleDao.getArticleForId(id);
	}

}
