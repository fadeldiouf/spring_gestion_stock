package com.fadel.gestiondestock.repository;

import com.fadel.gestiondestock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository< Article,Integer> {

}
