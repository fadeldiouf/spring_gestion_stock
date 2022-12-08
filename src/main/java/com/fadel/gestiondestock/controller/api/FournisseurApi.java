package com.fadel.gestiondestock.controller.api;

import com.fadel.gestiondestock.dto.FournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fadel.gestiondestock.utils.Contants.App_Root;
@Api(App_Root +"/fournisseur")
public interface FournisseurApi {
    @PostMapping(value =App_Root + "/fournisseurs/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Enregister un fournisseur",notes = "cette methode permet d'enregister ou modifier un fournisseur", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Fournisseur cree/modifier"),
            @ApiResponse(code = 400,message = "Fournisseur n'est pas valide")
    })
    FournisseurDto save(@RequestBody FournisseurDto fournisseurDto);

    @GetMapping(value =App_Root + "/fournisseurs/{idFournisseur}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher un fournisseur par ID ",notes = "cette methode permet de rechercher un fournisseur par son ID ", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Le fournisseur a été trouvée dans la BDD "),
            @ApiResponse(code = 404,message = "Aucun fournisseur n'existe dans la DBB avec l'ID fourni")
    })
    FournisseurDto findById(@PathVariable("idCategorie")Integer id);

    @GetMapping(value =App_Root + "/fournisseurs/all",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Revoie la liste des fournisseurs",notes = "cette methode permet de recherche et renvoyer la liste des fournisseurs qui exitent dans la BDD ", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La liste des fournisseurs/Une liste vide ")

    })
    List<FournisseurDto> findAll();

    @DeleteMapping(value =App_Root + "/fournisseurs/delete/{idFournisseur}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "suprimer un fournisseur ",notes = "cette methode permet de suprimer un fournisseur  ", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Le fournisseur a été suprimer ")
    })
    void delete(@PathVariable ("idFournisseur")Integer id);
}
