package com.libraryapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryapi.model.entity.Book;
import com.libraryapi.model.repository.BookRepository;
import com.libraryapi.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	
	private BookRepository repository;
	
	public BookServiceImpl(BookRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Book save(Book book) {		
		return repository.save(book);
	}

}
