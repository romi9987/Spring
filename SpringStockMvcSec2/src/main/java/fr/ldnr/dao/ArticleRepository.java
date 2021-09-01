package fr.ldnr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ldnr.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	

}
