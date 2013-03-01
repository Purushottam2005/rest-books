package com.habuma.books;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	HomeResourceAssembler resourceAssembler = new HomeResourceAssembler();
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody HomeResource startPoint() {
		HomeResource resource = resourceAssembler.toResource("The REST Books API");
		resource.add(ControllerLinkBuilder.linkTo(BooksController.class).withRel("books"));
		return resource;
	}
	
	private static final class HomeResourceAssembler extends ResourceAssemblerSupport<String, HomeResource> {
		public HomeResourceAssembler() {
			super(HomeController.class, HomeResource.class);
		}

		@Override
		public HomeResource toResource(String description) {
			return createResourceWithId(0, description);
		}
	}
	
}
