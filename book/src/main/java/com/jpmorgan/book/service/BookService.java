package com.jpmorgan.book.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jpmorgan.book.domain.entity.Book;
import com.jpmorgan.book.domain.repository.IBookRepository;
import com.jpmorgan.book.dto.BookDTO;
import com.jpmorgan.book.exceptionHandler.NotFoundException;


@Component
public class BookService implements IBookService{
	
	@Autowired
	private IBookRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	@Transactional
	public BookDTO create(BookDTO bookDTO) {
		Book book = repository.save(toDomainObject(bookDTO));
		return fromDomainObject(book);
	}
	
	@Override
	@Transactional
	public BookDTO read(String isbn) {
		return fromDomainObject(repository.findByIsbn(isbn));
	}
	
	@Override
	@Transactional
	public BookDTO update(String isbn, BookDTO bookDetails) {
		
		Book book = repository.findByIsbn(isbn);
		if(book != null) {
			book.setAuthor(bookDetails.getAuthor());
			book.setIsbn(bookDetails.getIsbn());
			book.setTags(bookDetails.getTags());
			book.setTitle(bookDetails.getTitle());
		}
		return fromDomainObject(repository.save(book));
	}

	@Override
	@Transactional
	public void delete(String isbn) {
		Book book = null;
		try {
			book = repository.findByIsbn(isbn);
			if(book == null) {
				throw new RuntimeException();
			}
		} catch(NotFoundException customException) {
			 new NotFoundException("Book", "isbn", isbn);
		}
		
		repository.delete(book);
	}

	@Override
	@Transactional
	public List<BookDTO> list() {
		List<Book> books = repository.findAll();
		return books.stream().map(this::fromDomainObject).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public BookDTO searchByAttriutes(String isbn, String title, String author, String tags) {
		return fromDomainObject(repository.findByIsbnOrTitleOrAuthorOrTags(isbn, title, author, tags));
	}
	
	@Override
	@Transactional
	public List<BookDTO> importBook(List<BookDTO> bookDetails) {
		bookDetails.stream().map(this::create).collect(Collectors.toList());
		return list();
	}
	
	private BookDTO fromDomainObject(Book book) {
		mapper.getConfiguration().setSkipNullEnabled(true);
	    return mapper.map(book, BookDTO.class);
	}
	
	private Book toDomainObject(BookDTO bookDTO) throws ParseException {
		mapper.getConfiguration().setSkipNullEnabled(true);
	    return mapper.map(bookDTO, Book.class);
	}
	
}
