package com.fadel.gestiondestock.repository;

import com.fadel.gestiondestock.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie,Integer> {
    Optional<Categorie> findArticleByCode(String Code);
}
