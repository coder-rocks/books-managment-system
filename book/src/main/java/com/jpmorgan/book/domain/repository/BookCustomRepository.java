package com.jpmorgan.book.domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.jpmorgan.book.domain.entity.Book;

public class BookCustomRepository implements IBookCustomRepository{
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Book findByIsbnOrTitleOrAuthorOrTags(String isbn, String title, String author, String tags) {
		
		String sql = "SELECT b FROM BOOK b WHERE b.isbn = :isbn OR b.title = :title OR b.author = :author OR b.tags = :tags";
		final TypedQuery<Book> query = entityManager.createQuery(sql, Book.class);
		query.setParameter("isbn", isbn);
		query.setParameter("title", title);
		query.setParameter("author", author);
		query.setParameter("tags", tags);
		return query.getSingleResult();
		
	}
	
	@Override
	public Book findByIsbn(String isbn) {
		
		String sql = "SELECT b FROM BOOK b WHERE b.isbn = :isbn";
		final TypedQuery<Book> query = entityManager.createQuery(sql, Book.class);
		query.setParameter("isbn", isbn);
		return query.getSingleResult();
		
	}
}
