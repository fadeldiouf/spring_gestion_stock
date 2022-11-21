package com.fadel.gestiondestock.controller.api;

import com.fadel.gestiondestock.dto.ArticleDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fadel.gestiondestock.utils.Contants.App_Root;

public interface ArticleApi {
    @PostMapping(value =App_Root + "/article/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
    ArticleDto save(@RequestBody ArticleDto articleDto);

    @GetMapping(value =App_Root + "/article/{idArticle}",produces =MediaType.APPLICATION_JSON_VALUE )
    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value =App_Root + "/article/{codeArticle}",produces =MediaType.APPLICATION_JSON_VALUE )
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value =App_Root + "/article/all",produces =MediaType.APPLICATION_JSON_VALUE )
    List<ArticleDto> findAll();

    @DeleteMapping(value =App_Root + "/article/delete/{idArticle}",produces =MediaType.APPLICATION_JSON_VALUE )
    void delete(@PathVariable("idArticle") Integer id);
}
