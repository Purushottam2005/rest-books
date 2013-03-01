package com.habuma.books;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class AuthorResourceAssembler extends ResourceAssemblerSupport<Author, AuthorResource> {

	public AuthorResourceAssembler() {
		super(AuthorsController.class, AuthorResource.class);
	}
	
	@Override
	public AuthorResource toResource(Author author) {
		return createResourceWithId(author.getId(), author);
	}
	
	@Override
	protected AuthorResource instantiateResource(Author author) {
		return new AuthorResource(author.getId(), author.getName(), author.getBio(), author.getTwitterName());
	}
	
}
