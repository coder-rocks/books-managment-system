package com.jpmorgan.book.domain.repository;

import com.jpmorgan.book.domain.entity.Book;

public interface IBookCustomRepository {
	
	public Book findByIsbnOrTitleOrAuthorOrTags(String isbn, String title, String author, String tags);
	
	public Book findByIsbn(String isbn);
}
