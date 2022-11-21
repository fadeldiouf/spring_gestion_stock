package com.fadel.gestiondestock.services.impl;

import com.fadel.gestiondestock.dto.ArticleDto;
import com.fadel.gestiondestock.exception.EntityNotFoundException;
import com.fadel.gestiondestock.exception.ErrorCodes;
import com.fadel.gestiondestock.exception.InvalidEntityException;
import com.fadel.gestiondestock.model.Article;
import com.fadel.gestiondestock.repository.ArticleRepository;
import com.fadel.gestiondestock.services.ArticleService;
import com.fadel.gestiondestock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;
    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository){
        this.articleRepository=articleRepository;
    }
    @Override
    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors= ArticleValidator.validate(articleDto);
        if (!errors.isEmpty()){
            log.error("Article is not valid {}",articleDto);
            throw new InvalidEntityException("l'article n'est pas valide ", ErrorCodes.ARTICLE_NOT_VALID);
        }
        return ArticleDto.fromEntity(
                articleRepository.save(
                        ArticleDto.toEntity(articleDto)
                ));
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (id==null){
            log.error("article ID is null");
            return null;
        }
        Optional<Article>article= articleRepository.findById(id);
        ArticleDto articleDto= ArticleDto.fromEntity(article.get());
        return Optional.of(articleDto).orElseThrow(()->new EntityNotFoundException(
                "aucun article avec l'ID="+ id +"n'a été trouver dans la BDD",
                ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if (!StringUtils.hasLength(codeArticle)){
            log.error("article CODE is null");
            return null;
        }
        Optional<Article>article= articleRepository.findArticleByCodeArticle(codeArticle);
        ArticleDto articleDto= ArticleDto.fromEntity(article.get());
        return Optional.of(articleDto).orElseThrow(()->new EntityNotFoundException(
                "aucun article avec le code="+ codeArticle +"n'a été trouver dans la BDD",
                ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null) {
            log.error("article ID is null");
            return;
        }
        articleRepository.deleteById(id);

    }

}
