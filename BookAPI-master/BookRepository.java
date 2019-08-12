package com.bcs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bcs.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findByBookName(String bookName);

}
