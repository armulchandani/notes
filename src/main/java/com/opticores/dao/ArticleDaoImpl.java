package com.opticores.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.opticores.exception.NoEntityFoundException;
import com.opticores.model.Article;
import com.opticores.model.User;

@Repository
public class ArticleDaoImpl extends HibernateDAO<Article> implements
		ArticleDao {

	/** Default constructor
	 * 
	 */
	public ArticleDaoImpl() {
		super(Article.class);
	}

	/** 
	 * {@inheritDoc}} 
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAllArticlesForUser(User user) {
		
		Query query= getSession().getNamedQuery("fetchArticlesByUserId");
		query.setParameter("user", user.getId());
		
		List<Article> articles= query.list();
		
		return articles;
	}
	
	
	/** 
	 * {@inheritDoc}} 
	 * 
	 */
	@Override
	public void addArticlesForUser(Article article) {
		addEntity(article);
		
	}
	
	/** 
	 * {@inheritDoc}} 
	 * 
	 */
	@Override
	public void updateArticle(Article article) {
		updateEntity(article);
		
	}
	
	/** 
	 * {@inheritDoc}} 
	 * @throws NoEntityFoundException 
	 * 
	 */
	@Override
	public void removeArticle(Integer articleId) throws NoEntityFoundException {
		deleteEntityById(articleId);
		
	}

	@Override
	public Article getArticleForId(Integer id) {
		return findById(id);
	}

}
