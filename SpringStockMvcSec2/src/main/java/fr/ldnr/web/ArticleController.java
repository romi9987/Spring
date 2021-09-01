package fr.ldnr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import fr.ldnr.dao.ArticleRepository;

@Controller
public class ArticleController {
	@Autowired
	ArticleRepository articleRepository;

	@GetMapping("/index")
	public String index() {
		return ("articles");
	}
}
