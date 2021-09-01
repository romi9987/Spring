package fr.ldnr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.ldnr.dao.ArticleRepository;
import fr.ldnr.entities.Article;

@SpringBootApplication
public class SpringStockMvcSec2Application implements CommandLineRunner{
	
	@Autowired
	private ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringStockMvcSec2Application.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception {
		articleRepository.save(new Article(null, "Samsung S9", 350));
		articleRepository.save(new Article(null, "Samsung S10", 500));
		articleRepository.save(new Article(null, "Huawei X4", 400));
		articleRepository.save(new Article(null, "Samsung S8", 250));
		articleRepository.save(new Article(null, "Samsung Galaxy Tab", 450));
		articleRepository.save(new Article(null, "Apple 11", 600));
		articleRepository.save(new Article(null, "Apple Ipad", 350));
		articleRepository.save(new Article(null, "Lenovo T430", 300));
		articleRepository.save(new Article(null, "HP ProBook", 350));
	}

}
