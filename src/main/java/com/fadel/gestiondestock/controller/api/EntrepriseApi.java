package com.fadel.gestiondestock.controller.api;

import com.fadel.gestiondestock.dto.EntrepriseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fadel.gestiondestock.utils.Contants.App_Root;

@Api(App_Root +"/entreprise")
public interface EntrepriseApi {
    @PostMapping(value =App_Root + "/entreprises/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Enregister une entreprise",notes = "cette methode permet d'enregister ou modifier une entreprise", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Entreprise cree/modifier"),
            @ApiResponse(code = 400,message = "Entreprise n'est pas valide")
    })
    EntrepriseDto save(@RequestBody EntrepriseDto entrepriseDto);

    @GetMapping(value =App_Root + "/entreprises/{idEntreprise}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher une entreprise par ID ",notes = "cette methode permet de rechercher une entreprise par son ID ", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L' entreprise a été trouvée dans la BDD "),
            @ApiResponse(code = 404,message = "Aucune entreprise n'existe dans la DBB avec l'ID fourni")
    })

    EntrepriseDto findById(@PathVariable ("idEntreprise") Integer id);
    @GetMapping(value =App_Root + "/entreprises/all",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Revoie la liste des entreprises",notes = "cette methode permet de recherche et renvoyer la liste des entreprises qui exitent dans la BDD ", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La liste des entreprises/Une liste vide ")

    })
    List<EntrepriseDto> findAll();

    @DeleteMapping(value =App_Root + "/entreprises/delete/{idEntreprise}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "suprimer une entreprise ",notes = "cette methode permet de suprimer une entreprise  ", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L' entreprise a été suprimer ")
    })
    void delete(@PathVariable("idEntreprise") Integer id);
}
