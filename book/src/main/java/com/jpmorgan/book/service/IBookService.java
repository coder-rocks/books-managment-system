package com.jpmorgan.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpmorgan.book.dto.BookDTO;


@Service
public interface IBookService {
	BookDTO create(BookDTO book);
	BookDTO read(String isbn);
	BookDTO update(String isbn, BookDTO book);
	void delete(String isbn);
	List<BookDTO> list();
	BookDTO searchByAttriutes(String isbn, String title, String author, String tags);
	List<BookDTO> importBook(List<BookDTO> books);
}
