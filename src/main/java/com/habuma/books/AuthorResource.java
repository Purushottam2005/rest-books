package com.habuma.books;

import org.springframework.hateoas.ResourceSupport;

public class AuthorResource extends ResourceSupport {
	private long id;
	private String name;
	private String bio;
	private String twitterName;

	public AuthorResource(long id, String name, String bio, String twitterName) {
		this.id = id;
		this.name = name;
		this.bio = bio;
		this.twitterName = twitterName;
	}
	
	public long getAuthorId() {
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
