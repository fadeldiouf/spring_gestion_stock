package com.fadel.gestiondestock.repository;

import com.fadel.gestiondestock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository< Article,Integer> {
    Optional<Article> findArticleByCodeArticle(String CodeArticle);

}
