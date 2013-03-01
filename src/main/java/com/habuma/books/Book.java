package com.habuma.books;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_BOOK")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private String title;
	
	@ManyToOne
	private Author author;

	public Book() {}
	
	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Author getAuthor() {
		return author;
	}

}
