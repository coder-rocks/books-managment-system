package com.jpmorgan.book.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jpmorgan.book.dto.BookDTO;
import com.jpmorgan.book.exceptionHandler.BadRequestException;
import com.jpmorgan.book.exceptionHandler.NotFoundException;
import com.jpmorgan.book.service.IBookService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private IBookService service;
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "List books from the system", notes = "Retrieves all the books currently present in the system")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = BookDTO.class, message = "Book Added Successfully"),
			@ApiResponse(code = 400, response = BadRequestException.class, message = "Validation Failure")})
	public List<BookDTO> list() {
	    return service.list();
	}
	
	/*Access the books by ISBN*/
	@GetMapping(path = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Fetch book from the system.", notes = "Get book details using the provided ISBN.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, response = BookDTO.class, message = "Book Retreived Successfully"),
			@ApiResponse(code = 400, response = BadRequestException.class, message = "Validation Failure"),
			@ApiResponse(code = 404, response = NotFoundException.class, message = "Resource Not Available")})
	public BookDTO read(@PathVariable(value = "isbn") String isbn) {
	    return service.read(isbn);
	}
	
	/*•	Search API by any of the attributes of the books*/
	@GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Search book by any of its attributes.", notes = "Lookup book details in the sytem with the provided one or more attributes.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, response = BookDTO.class, message = "Book Added Successfully"),
			@ApiResponse(code = 400, response = BadRequestException.class, message = "Validation Failure"),
			@ApiResponse(code = 404, response = NotFoundException.class, message = "Resource Not Available")})
	public BookDTO searchBookByAttributes(@RequestParam(value ="isbn", defaultValue = "empty") String isbn, @RequestParam(value = "title", defaultValue = "empty") String title, @RequestParam(value = "author", defaultValue = "empty") String author, @RequestParam(value = "tags", defaultValue = "empty") String tags) {
		return service.searchByAttriutes(isbn, title, author, tags);
	}
	
	
	@PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Add bookDetails in the system.", notes = "Creates a book entry from the details provided.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, response = BookDTO.class, message = "Book Added Successfully"),
			@ApiResponse(code = 400, response = BadRequestException.class, message = "Validation Failure")})
	public BookDTO addBookDetails(@RequestBody BookDTO book) {
	    return service.create(book);
	}

	
	@PutMapping(path = "/{isbn}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update book details in the system.", notes = "Updates book details for the provided ISBN.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, response = BookDTO.class, message = "Book Added Successfully"),
			@ApiResponse(code = 400, response = BadRequestException.class, message = "Validation Failure"),
			@ApiResponse(code = 404, response = NotFoundException.class, message = "Resource Not Available")})
	public BookDTO updateBookDetails(@PathVariable(value = "isbn") String isbn, @RequestBody BookDTO bookDetails) {
	    return service.update(isbn, bookDetails);
	}
	
	@DeleteMapping("/{isbn}")
	@ApiOperation(value = "delete book details from the system.", notes = "Deletes a book record for the provided ISBN.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, response = BookDTO.class, message = "Book Added Successfully"),
			@ApiResponse(code = 400, response = BadRequestException.class, message = "Validation Failure"),
			@ApiResponse(code = 404, response = NotFoundException.class, message = "Resource Not Available")})
	public ResponseEntity<?> deleteBookDetails(@PathVariable(value = "isbn") String isbn) {
		service.delete(isbn);
	    return ResponseEntity.ok().build();
	}
	
	@PostMapping(path = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "import book details from CSV", notes = "import book details by parsing CSV to uploading details and returns list of books.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, response = BookDTO.class,  responseContainer = "List", message = "Book Added Successfully"),
			@ApiResponse(code = 400, response = BadRequestException.class, message = "Validation Failure")})
	public List<BookDTO> importBookDetails(@RequestParam("file") MultipartFile file) {
		List<BookDTO> bookDetails = null;
	        if (!file.isEmpty()) {
	            Reader reader;
				try {
					reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
					
					@SuppressWarnings({ "unchecked", "rawtypes" })
					CsvToBean<BookDTO> csvToBean = new CsvToBeanBuilder(reader).withType(BookDTO.class).withIgnoreLeadingWhiteSpace(true).build();

		            bookDetails = service.importBook(csvToBean.parse());
		                      
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    return bookDetails;
	}
}
