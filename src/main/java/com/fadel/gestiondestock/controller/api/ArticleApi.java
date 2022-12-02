package com.fadel.gestiondestock.controller.api;

import com.fadel.gestiondestock.dto.ArticleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fadel.gestiondestock.utils.Contants.App_Root;

@Api(App_Root + "/article")
public interface ArticleApi {
    @PostMapping(value =App_Root + "/article/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Enregister un article",notes = "cette methode permet d'enregister ou modifier un article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Article cree/modifier"),
            @ApiResponse(code = 400,message = "Article n'est pas valide")
    })
    ArticleDto save(@RequestBody ArticleDto articleDto);

    @GetMapping(value =App_Root + "/article/{idArticle}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher un article par ID ",notes = "cette methode permet de rechercher un article par son ID ", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'article a été trouvée dans la BDD "),
            @ApiResponse(code = 404,message = "Aucun article n'existe dans la DBB avec l'ID fourni")
    })
    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value =App_Root + "/article/{codeArticle}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher un article  par code",notes = "cette methode permet de rechercher un article par son code ", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'article a été trouvée dans la BDD "),
            @ApiResponse(code = 404,message = "Aucun article n'existe dans la DBB avec le code fourni")
    })
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value =App_Root + "/article/all",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Revoie la liste des articles",notes = "cette methode permet de recherche et renvoyer la liste des acticles qui exitent dans la BDD ", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La liste des articles/Une liste vide ")

    })
    List<ArticleDto> findAll();

    @DeleteMapping(value =App_Root + "/article/delete/{idArticle}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "suprimer un article ",notes = "cette methode permet de suprimer un article  ", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'article a été suprimer ")
    })
    void delete(@PathVariable("idArticle") Integer id);
}
