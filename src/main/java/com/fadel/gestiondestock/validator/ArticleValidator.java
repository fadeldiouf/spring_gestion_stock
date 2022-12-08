package com.fadel.gestiondestock.validator;

import com.fadel.gestiondestock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {
    public  static List<String> validate(ArticleDto articleDto){
        List<String> errors = new ArrayList<>();
        if (articleDto==null){
            errors.add("veuiller renseigner le code de L'article");
            errors.add("veuiller renseigner la designation de L'article");
            errors.add("veuiller renseigner le prix unitaire de L'article");
            errors.add("veuiller selectionner une photo");
            errors.add("veuiller selectionner une categorie");
            return errors;
        }
        if (!StringUtils.hasLength(articleDto.getCodeArticle())){
            errors.add("veuiller renseigner le code de L'article");
        }
        if (!StringUtils.hasLength(articleDto.getDesignation())){
            errors.add("veuiller renseigner la designation de L'article");
        }
        if (articleDto.getPrixUnitaire()==null){
            errors.add("veuiller renseigner le prix unitaire de L'article");
        }
        if (articleDto.getPhoto()==null){
            errors.add("veuiller selectionner une photo");
        }
        if (articleDto.getCategorie()==null){
            errors.add("veuiller selectionner une categorie");
        }
        return  errors;
    }
}
