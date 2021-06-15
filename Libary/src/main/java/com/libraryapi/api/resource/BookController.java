package com.libraryapi.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.libraryapi.api.dto.BookDTO;
import com.libraryapi.exception.ApiErrors;
import com.libraryapi.exception.BusinesException;
import com.libraryapi.model.entity.Book;
import com.libraryapi.service.BookService;



@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private BookService service;
	private ModelMapper modelMapper;
	
	public BookController(BookService service, ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BookDTO create(@Valid @RequestBody BookDTO dto){		
		
		Book entity = modelMapper.map(dto, Book.class);
		
		entity = service.save(entity);		
		return modelMapper.map(entity, BookDTO.class);
		//return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationException(MethodArgumentNotValidException ex) {
		
		BindingResult bindinResult =  ex.getBindingResult();
		//List<ObjectError> allErrors =  bindinResult.getAllErrors();
		
		return new ApiErrors(bindinResult);
	}
	
	@ExceptionHandler(BusinesException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleBusinessException(BusinesException ex) {
		
		//BindingResult bindinResult =  ex.getBindingResult();
		//List<ObjectError> allErrors =  bindinResult.getAllErrors();
		
		return new ApiErrors( ex);
	}
}
