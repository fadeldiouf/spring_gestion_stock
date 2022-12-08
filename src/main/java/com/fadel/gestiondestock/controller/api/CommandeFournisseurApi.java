package com.fadel.gestiondestock.controller.api;

import com.fadel.gestiondestock.dto.CommandeFournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fadel.gestiondestock.utils.Contants.App_Root;

@Api(App_Root + "/commandeFournisseurs")
public interface CommandeFournisseurApi {
    @PostMapping(value =App_Root + "/commandeFournisseurs/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Enregister une commandeFournisseur",notes = "cette methode permet d'enregister ou modifier une commandeFournisseur", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "CommandeFournisseur cree/modifier"),
            @ApiResponse(code = 400,message = "CommandeFournisseur n'est pas valide")
    })
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    @GetMapping(value =App_Root + "/commandeFournisseurs/{idCommandeFournisseur}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher une commandeFournisseur par ID ",notes = "cette methode permet de rechercher une commandeFournisseur par son ID ", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La commandeFournisseur a été trouvée dans la BDD "),
            @ApiResponse(code = 404,message = "Aucune commandeFournisseur n'existe dans la DBB avec l'ID fourni")
    })
    CommandeFournisseurDto findById(@PathVariable("idCommandeFournisseur")Integer id);

    @GetMapping(value =App_Root + "/commandeFournisseurs/commandeFournisseur/{codeCommandeFournisseur}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher une commandeFournisseur  par code",notes = "cette methode permet de rechercher une commandeFournisseur par son code ", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La commandeFournisseur a été trouvée dans la BDD "),
            @ApiResponse(code = 404,message = "Aucune  commandeFournisseur n'existe dans la DBB avec le code fourni")
    })
    CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @GetMapping(value =App_Root + "/commandeFournisseurs/all",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Revoie la liste des commandeFournisseurs",notes = "cette methode permet de recherche et renvoyer la liste des commandeFournisseurs qui exitent dans la BDD ", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La liste des commandeFournisseurs/Une liste vide ")

    })
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(value =App_Root + "/commandeFournisseurs/delete/{idCommandeFournisseur}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "suprimer une commandeFournisseur ",notes = "cette methode permet de suprimer une commandeFournisseur  ", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La commandeFournisseur a été suprimer ")
    })
    void delete(@PathVariable("idCommandeFournisseur") Integer id);
}
