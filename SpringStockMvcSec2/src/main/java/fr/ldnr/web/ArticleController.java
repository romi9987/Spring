package fr.ldnr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.entities.Article;

@Controller
public class ArticleController {
	@Autowired
	ArticleRepository articleRepository;

	//Etape 9 - Utilisation de Spring Data
		//En effet, on souhaite ajouter dans articles.html 
		//un formulaire permettant d'afficher tous les articles contenant un mot clé
		//<form th:action="@{/index}" method="get"> = validation du formulaire, appel de la méthode index
		
		//Dans ArticleRepository on ajoute methode findByDescriptionContains(String description, Pageable pageable)
		
		@GetMapping("/index")
		public String index(Model model, @RequestParam(name="page", defaultValue="0") int page,
										@RequestParam(name="keyword", defaultValue="") String kw) {
			Page<Article> articles = articleRepository.findByDescriptionContains(kw, PageRequest.of(page, 4));
			model.addAttribute("listArticle", articles.getContent());
			model.addAttribute("pages", new int[articles.getTotalPages()]);
			model.addAttribute("currentPage", page);
			
		//On souhaite au passage que le mot clé reste dans la zone de saisie:
			//donc dans le contrôleur il faut ajouter un attribut correspondant dans le model
			model.addAttribute("keyword", kw);
			return "articles";
		}
		
		//Dans la vue, articles.html, il faut afficher l'attribut keyword
		//<input type = "text" name = "keyword" th:value = "${keyword}">
		// keyword = le mot clé pour obtenir le résultat de la recherche
		
		//et rajouter dans la pagination le mot clé keyword
		//th:href = "@{/index(page=${status.index}, - lien vers un indice page
		//keyword=${keyword})}"	- mot clé = mot clé courant
		//th:text = "${status.index}"> = afficher l'indice du tableau
			
		// car si on veut naviguer sur le résultat de la recherche,
		//il va afficher tous les articles sans filtre
}
