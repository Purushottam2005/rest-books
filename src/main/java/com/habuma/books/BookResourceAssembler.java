package com.habuma.books;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class BookResourceAssembler extends ResourceAssemblerSupport<Book, BookResource> {

	public BookResourceAssembler() {
		super(BooksController.class, BookResource.class);
	}

	@Override
	public BookResource toResource(Book book) {
		return createResourceWithId(book.getId(), book);
	}

	@Override
	protected BookResource instantiateResource(Book book) {
		return new BookResource(book.getId(), book.getTitle(), book.getAuthor().getName());
	}

}
