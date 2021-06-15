package com.libraryapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libraryapi.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
