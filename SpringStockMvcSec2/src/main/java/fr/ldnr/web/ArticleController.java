package fr.ldnr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.entities.Article;

@Controller
public class ArticleController {
	@Autowired
	ArticleRepository articleRepository;

//	@GetMapping("/index")
//	public String index() {
//		return ("articles");
//	}
	
	@GetMapping("/index")
		public String index(Model model) {
			List<Article> articles = articleRepository.findAll();
			
			model.addAttribute("listArticle", articles); 
														
			return "articles";
		}
}
