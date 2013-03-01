package com.habuma.books;

import org.springframework.hateoas.ResourceSupport;

public class BookResource extends ResourceSupport {
	
	private long id;
	private String title;
	private String author;
	
	public long getBookId() {
		return (int) id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public BookResource(long id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}
	
}
