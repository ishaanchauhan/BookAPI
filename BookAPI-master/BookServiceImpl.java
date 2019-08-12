package com.bcs.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.bcs.model.Book;
import com.bcs.repository.BookRepository;
import com.bcsutilities.CustomizedPager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("bookService")
public class BookServiceImpl {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	ObjectMapper objMapper;

	@Transactional
	public String findBookByID(long id) throws JsonProcessingException, Exception {
		String jsonReturn = null;
		Book books = new Book();
		books = bookRepository.getOne(id);
		jsonReturn = objMapper.writeValueAsString(books);
		return jsonReturn;
	}

	@Transactional
	public Book findBookByIDD(long id) throws Exception {
		Book books = new Book();
		books = bookRepository.getOne(id);
		return books;
	}

	@Transactional
	public boolean isBookExists(long id) throws Exception {
		return bookRepository.exists(id);
	}

	@Transactional
	public String findAllBooks1() throws JsonProcessingException, Exception {
		String jsonReturn = null;
		List<Book> books = new ArrayList<Book>();
		books = bookRepository.findAll();
		jsonReturn = objMapper.writeValueAsString(books);
		return jsonReturn;
	}

	@Transactional
	public void saveBook(Book book) throws Exception {
		bookRepository.save(book);
	}

	@Transactional
	public void deleteBookById(long id) throws Exception {
		bookRepository.delete(id);
	}

	@Transactional
	public List<Book> isBookExist(Book book) throws Exception {
		return bookRepository.findByBookName(book.getBookName());
	}

	@Transactional
	public void deleteAllBook() throws Exception {
		bookRepository.deleteAll();
	}

	@Transactional
	public List<Book> findPagedBooks(int limit, int offset) throws Exception {
		Pageable objPageable = new CustomizedPager(limit, offset);
		return bookRepository.findAll(objPageable).getContent();
	}

}
