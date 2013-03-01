package com.habuma.books;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_AUTHOR")
public class Author {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String bio;
	private String twitterName;
	
	public Author() {}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getBio() {
		return bio;
	}

	public String getTwitterName() {
		return twitterName;
	}

}
