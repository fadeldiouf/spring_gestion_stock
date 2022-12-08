package com.fadel.gestiondestock.controller.api;

import com.fadel.gestiondestock.dto.CategorieDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fadel.gestiondestock.utils.Contants.App_Root;

@Api(App_Root +"/categorie")
public interface CategorieApi {
    @PostMapping(value =App_Root + "/categories/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Enregister un categorie",notes = "cette methode permet d'enregister ou modifier un categorie", response = CategorieDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Categorie cree/modifier"),
            @ApiResponse(code = 400,message = "Categorie n'est pas valide")
    })
    CategorieDto save(@RequestBody CategorieDto categorieDto);
    @GetMapping(value =App_Root + "/categories/{idCategorie}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher un categorie par ID ",notes = "cette methode permet de rechercher un categorie par son ID ", response = CategorieDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'categorie a été trouvée dans la BDD "),
            @ApiResponse(code = 404,message = "Aucune categorie n'existe dans la DBB avec l'ID fourni")
    })
    CategorieDto findById( @PathVariable ("idCategorie") Integer id);
    @GetMapping(value =App_Root + "/categories/categorie/{codeCategorie}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher un categorie  par code",notes = "cette methode permet de rechercher un categorie par son code ", response = CategorieDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La categorie a été trouvée dans la BDD "),
            @ApiResponse(code = 404,message = "Aucune categorie n'existe dans la DBB avec le code fourni")
    })
    CategorieDto findByCode(@PathVariable ("codeCategorie") String code);

    @GetMapping(value =App_Root + "/categories/all",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Revoie la liste des categories",notes = "cette methode permet de recherche et renvoyer la liste des categories qui exitent dans la BDD ", response = CategorieDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La liste des categories/Une liste vide ")

    })
    List<CategorieDto> findAll();

    @DeleteMapping(value =App_Root + "/categories/delete/{idCategorie}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "suprimer un categorie ",notes = "cette methode permet de suprimer un categorie  ", response = CategorieDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'categorie a été suprimer ")
    })
    void delete(@PathVariable ("idCategorie")Integer id);
}
