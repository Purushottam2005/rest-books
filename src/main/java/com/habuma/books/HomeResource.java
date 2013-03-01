package com.habuma.books;

import org.springframework.hateoas.ResourceSupport;

public class HomeResource extends ResourceSupport {
	
	public String getDescription() {
		return "The REST Books API";
	}
	
}
