package com.opticores.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedNativeQueries({ @NamedNativeQuery(name = "fetchArticlesByUserId", query = "select * from article a where a.user=:user", resultClass = Article.class) })
public class Article extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 679431641771009740L;

	/**
	 * 
	 */
	public Article() {

	}

	@Column
	private String title;

	@Column
	private String article;

	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	private Timestamp created;

	@Column
	private Timestamp updated;

	@ManyToOne
	@JoinColumn(name = "user")
	@JsonIgnore
	private User user;

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
