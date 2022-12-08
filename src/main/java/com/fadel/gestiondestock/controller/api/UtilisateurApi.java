package com.fadel.gestiondestock.controller.api;

import com.fadel.gestiondestock.dto.UtilisateurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fadel.gestiondestock.utils.Contants.App_Root;
@Api(App_Root +"/utilisateur")
public interface UtilisateurApi {
    @PostMapping(value =App_Root + "/utilisateurs/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Enregister un utilisateur",notes = "cette methode permet d'enregister ou modifier un utilisateur", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Utilisateur cree/modifier"),
            @ApiResponse(code = 400,message = "Utilisateur n'est pas valide")
    })
    UtilisateurDto save(@RequestBody UtilisateurDto utilisateurDto);

    @GetMapping(value =App_Root + "/utilisateurs/{idUtilisateur}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher un utilisateur par ID ",notes = "cette methode permet de rechercher un utilisateur par son ID ", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L' utilisateur a été trouvée dans la BDD "),
            @ApiResponse(code = 404,message = "Aucun utilisateur n'existe dans la DBB avec l'ID fourni")
    })
    UtilisateurDto findById(@PathVariable("idCategorie")Integer id);

    @GetMapping(value =App_Root + "/utilisateurs/all",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Revoie la liste des utilisateurs",notes = "cette methode permet de recherche et renvoyer la liste des utilisateurs qui exitent dans la BDD ", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La liste des utilisateurs/Une liste vide ")

    })
    List<UtilisateurDto> findAll();

    @DeleteMapping(value =App_Root + "/utilisateurs/delete/{idUtilisateur}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "suprimer un utilisateur ",notes = "cette methode permet de suprimer un utilisateur  ", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L' utilisateur a été suprimer ")
    })
    void delete(@PathVariable ("idUtilisateur")Integer id);
}
