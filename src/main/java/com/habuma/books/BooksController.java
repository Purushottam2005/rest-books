package com.habuma.books;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;

import javax.inject.Inject;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/books")
@ExposesResourceFor(Book.class)
public class BooksController {

	private final BookRepository bookRepository;

	private BookResourceAssembler resourceAssembler = new BookResourceAssembler();
	
	@Inject
	public BooksController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Iterable<BookResource> allBooks() {
		return resourceAssembler.toResources(bookRepository.findAll());
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<?> bookById(@PathVariable("id") long id) throws Exception {
		Book book = bookRepository.findOne(id);
		if (book == null) {
			return new ResponseEntity<ApiError>(new ApiError(404, "Book [id=" + id + "] not found."), HttpStatus.NOT_FOUND);
		}
		BookResource bookResource = resourceAssembler.toResource(book);
		bookResource.add(linkTo(BooksController.class).withRel("all"));
		bookResource.add(linkTo(AuthorsController.class).slash(book.getAuthor().getId()).withRel("author"));
		return new ResponseEntity<BookResource>(bookResource, HttpStatus.OK);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
		Book book = bookRepository.findOne(id);
		bookRepository.delete(book);
		return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<BookResource> createBook(@RequestBody Book book) {
		bookRepository.save(book);
		String location = "http://localhost:8080/books/books/" + book.getId();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(location));
		ResponseEntity<BookResource> response = new ResponseEntity<BookResource>(resourceAssembler.toResource(book), headers, HttpStatus.CREATED);
		return response;
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public ResponseEntity<String> updateBook(@RequestBody Book book) {
		bookRepository.save(book);
		return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
	}
	
}
