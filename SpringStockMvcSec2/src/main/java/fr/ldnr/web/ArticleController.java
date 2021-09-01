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

	//Etape 8 - Mise en oeuvre de la pagination
		//La vue ici peut soliciter notre contrôleur pour lui indiquer que telle page
		//doit être affichée: http://localhost:8080/index?page=1
		//Reste à celui-ci de renvoyer la bonne page avec un nombre limité
		//d'articles par pages comme ci-dessous PageRequest.of(page, 5)

		@GetMapping("/index") //dans une servlet on utilisait request.getParameter("page")
		public String index(Model model, @RequestParam(name="page", defaultValue="0") int page) {
			Page<Article> articles = articleRepository.findAll(PageRequest.of(page, 5));
//			//en retour, au lieu d'une liste d'articles, on a tous les articles 
//			//formatées en page pointant sur la page demandée 
			model.addAttribute("listArticle", articles.getContent()); //pour récuperer sous forme de liste la page pointée
			
//			//pour afficher des liens de pagination permettant à l'utilisateur de passer 
//			//d'une page à l'autre, il faut:
//			//- récuperer le nombre total de pages
//			//- l'injecter dans le model sous forme de tableau d'entier
//			//- sur la partie html il suffira de boucler sur ce tableau pour afficher toutes les pages
			model.addAttribute("pages", new int[articles.getTotalPages()]);
			
//			//s'agissant de l'activation des liens de navigation, il faut transmettre à la vue la page courante
//			//thymeleaf pourra delors vérifier si la page courante est égal à l'index de la page active
//			<nav aria-label="Page navigation">
//			  	<ul class = "pagination">
//			  		<li class="page-item"><a class="page-link" href = "#">Previous</a></li>
//					<li 
//					th:class = "${currentPage==status.index}?'page-item active'" 
//					th:each = "page, status:${pages}">
//					<!-- 1. pour chaque indice de notre tableau "de pages" -->
//						<a class = "page-link px-3 text-dark" 
//						th:href = "@{/index(page=${status.index}
//						th:text = "${status.index}"> 
//						<!-- 2. afficher l'indice du tableau -->
//						<!-- 3. @{/index(page=${status.index} - lien vers un indice page -->
//						
//						</a>
//					</li>
//					<li class="page-item"><a href="#" class="page-link">Next</a></li>
					
//				</ul>
//			</nav>
			model.addAttribute("currentPage", page);
			
			return "articles";
		}
		
		//Indice de la page cliqué
		//En résumé, pour chaque balise <li> de notre boucle dans articles.html,
		//nous affichons l'index compris entre 0 et le nombre total de pages.
		//Chaque index renvoie vers la page correspondante et l'index de la page
		//courante est activé ou visible:
						// /index?page=0
						// /index?page=1
						// /index?page=2
}
