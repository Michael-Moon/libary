package com.libraryapi.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.libraryapi.model.entity.Book;
import com.libraryapi.model.repository.BookRepository;
import com.libraryapi.service.impl.BookServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {
	
	BookService service;
	
	@MockBean
	BookRepository repository;
	
	@BeforeEach
	public void setUp() {
		this.service = new BookServiceImpl(repository);
	}
	
	@Test
	@DisplayName("Deve salvar um livro")
	public void saveBookTest() {
		
		Book book = Book.builder()
				.author("Fulano")
				.title("As aventuras de fulano")
				.isbn("1213212")
				.build();
		
		Mockito.when(repository.save(book) ).thenReturn(Book.builder()
				.id(12L)
				.author("Fulano")
				.title("As aventuras de fulano")
				.isbn("1213212")
				.build());
		
		Book saveBook = service.save(book);
		
		assertThat(saveBook.getId()).isNotNull();
		assertThat(saveBook.getTitle()).isEqualTo("As aventuras de fulano");
		assertThat(saveBook.getIsbn()).isEqualTo("1213212");
		assertThat(saveBook.getAuthor()).isEqualTo("Fulano");
	}
	
	@Test
	@DisplayName("Deve lançar error de validação quando não houver dados suficiente para criar um livro")
	public void createInavalidBookTest() throws Exception {
		
	}
}
