package com.habuma.books;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/authors")
public class AuthorsController {

	private AuthorRepository authorRepository;

	private AuthorResourceAssembler authorResourceAssembler = new AuthorResourceAssembler();
	private BookResourceAssembler bookResourceAssembler = new BookResourceAssembler();

	private BookRepository bookRepository;

	@Inject
	public AuthorsController(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<AuthorResource> allAuthors() {
		return authorResourceAssembler.toResources(authorRepository.findAll());		
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> authorById(@PathVariable("id") long id) {
		Author author = authorRepository.findOne(id);
		if (author == null) {
			return new ResponseEntity<ApiError>(new ApiError(404, "Author [id=" + id + "] not found."), HttpStatus.NOT_FOUND);
		} 		
		AuthorResource resource = authorResourceAssembler.toResource(author);
		resource.add(linkTo(AuthorsController.class).withRel("all"));
		resource.add(linkTo(AuthorsController.class).slash(id).slash("books").withRel("books"));
		return new ResponseEntity<AuthorResource>(resource, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}/books", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> booksByAuthorId(@PathVariable("id") long id) {
		Author author = authorRepository.findOne(id);
		if (author == null) {
			return new ResponseEntity<ApiError>(new ApiError(404, "Author [id=" + id + "] not found."), HttpStatus.NOT_FOUND);
		} 		
		List<Book> authorBooks = bookRepository.findByAuthorId(id);
		List<BookResource> bookResources = bookResourceAssembler.toResources(authorBooks);
		return new ResponseEntity<List<BookResource>>(bookResources, HttpStatus.OK);
	}

}
