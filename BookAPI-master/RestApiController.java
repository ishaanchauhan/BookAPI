package com.bcs.controller;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.bcs.model.Book;
import com.bcs.service.BookServiceImpl;
import com.bcsutilities.RestSuccesType;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	BookServiceImpl bookServiceImpl;

	// Method to Create a Book
	@RequestMapping(value = "/book/", method = RequestMethod.POST)
	public ResponseEntity<?> createBook(@Valid @RequestBody Book book, UriComponentsBuilder ucBuilder)	throws Exception {
		logger.info("Creating Book : {}", book);

		if (!bookServiceImpl.isBookExist(book).isEmpty()) {
			logger.error("Unable to create. A Book with name {} already exist", book.getBookName());
			return new ResponseEntity<>(
					new RestSuccesType("Unable to create. A Book with name " + book.getBookName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		bookServiceImpl.saveBook(book);
		return new ResponseEntity<>(new RestSuccesType("Book has been created with name " + book.getBookName() + " ."),
				HttpStatus.CREATED);
	}

	// Method to find all books
	@RequestMapping(value = "/book/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listAllBook() throws JsonProcessingException, Exception {
		String bookJson = bookServiceImpl.findAllBooks1();
		if (bookJson == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(bookJson, HttpStatus.OK);
	}

	// Method to Retrieve Single Book
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getBook(@Valid @PathVariable("id") long id) throws Exception {
		logger.info("Fetching Book with id {}", id);
		String bookJson = bookServiceImpl.findBookByID(id);
		if (bookJson == null) {
			logger.error("Book with id {} not found.", id);
			return new ResponseEntity<>(new RestSuccesType("Book with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(bookJson, HttpStatus.OK);
	}

	// Method to Update a Book
	@RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBook(@Valid @PathVariable("id") long id, @RequestBody Book book) throws Exception {
		logger.info("Updating Book with id {}", id);

		Book currentBook = bookServiceImpl.findBookByIDD(id);
		if (currentBook == null) {
			logger.error("Unable to update. Book with id {} not found.", id);
			return new ResponseEntity<>(new RestSuccesType("Unable to update. Book with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentBook.setBookName(book.getBookName());
		currentBook.setAuthor(book.getAuthor());
		currentBook.setPrice(book.getPrice());

		bookServiceImpl.saveBook(currentBook);
		return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
	}

	// Method to Delete a Book
	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBook(@Valid @PathVariable("id") long id) throws Exception {
		logger.info("Fetching & Deleting Book with id {}", id);

		boolean isBookExist = bookServiceImpl.isBookExists(id);
		if (!isBookExist) {
			logger.error("Unable to delete. Book with id {} not found.", id);
			return new ResponseEntity<>(new RestSuccesType("Unable to delete. Book with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		bookServiceImpl.deleteBookById(id);
		return new ResponseEntity<>(new RestSuccesType("Book id " + id + " Deleted."), HttpStatus.OK);
	}

	// method to delete all books
	@RequestMapping(value = "/book/", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllBook() throws Exception {
		logger.info("Deleting All Books");

		bookServiceImpl.deleteAllBook();
		return new ResponseEntity<>(new RestSuccesType("All Book Deleted."), HttpStatus.OK);
	}

	// Method for paging on the basis of limit and offset
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public ResponseEntity<?> getPagedBook(@Valid @RequestParam(required = false, name = "limit") int limit,
			@Valid @RequestParam(required = false, name = "offset") int offset) throws Exception {
		List<Book> books = bookServiceImpl.findPagedBooks(limit, offset);
		if (books.isEmpty()) {
			return new ResponseEntity<>(new RestSuccesType("No records found for given criteria"),
					HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
}