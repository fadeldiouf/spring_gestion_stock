package com.fadel.gestiondestock.dto;

import com.fadel.gestiondestock.model.Article;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;

@Data
@Builder
public class ArticleDto {
    private Integer id;

    private String codeArticle;
    private String designation;
    private BigDecimal prixUnitaire;
    private  String photo;
    private CategorieDto categorie;
    private Integer idEntreprise;

    public static ArticleDto fromEntity(Article article){
        if (article==null){
            return null;
        }
        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUnitaire(article.getPrixUnitaire())
                .photo(article.getPhoto())
                .categorie(CategorieDto.fromEntity(article.getCategorie()))
                .idEntreprise(article.getIdEntreprise())
                .build();

    }
    public static Article toEntity(ArticleDto articleDto){
        if (articleDto==null){
            return null;
        }
        Article article= new Article();
        article.setId(articleDto.getId());
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaire(articleDto.getPrixUnitaire());
        article.setPhoto(articleDto.getPhoto());
        article.setIdEntreprise(articleDto.getIdEntreprise());
        return article;

    }
}
