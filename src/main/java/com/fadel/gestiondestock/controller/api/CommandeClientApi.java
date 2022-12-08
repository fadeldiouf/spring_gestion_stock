package com.fadel.gestiondestock.controller.api;

import com.fadel.gestiondestock.dto.CommandeClientDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fadel.gestiondestock.utils.Contants.App_Root;

public interface CommandeClientApi {
    @PostMapping(value =App_Root + "/commandeClients/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Enregister un commandeClient",notes = "cette methode permet d'enregister ou modifier un commandeClient", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "CommandeClient cree/modifier"),
            @ApiResponse(code = 400,message = "CommandeClient n'est pas valide")
    })
    CommandeClientDto save(@RequestBody CommandeClientDto commandeClientDto);
    @GetMapping(value =App_Root + "/commandeClients/{idCommandeClient}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher un commandeClient par ID ",notes = "cette methode permet de rechercher un commandeClient par son ID ", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'commandeClient a été trouvée dans la BDD "),
            @ApiResponse(code = 404,message = "Aucun commandeClient n'existe dans la DBB avec l'ID fourni")
    })
    CommandeClientDto findById(@PathVariable("idCommandeClient") Integer id);
    @GetMapping(value =App_Root + "/commandeClients/commandeClient/{codeCommandeClient}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher un commandeClient  par code",notes = "cette methode permet de rechercher un commandeClient par son code ", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'commandeClient a été trouvée dans la BDD "),
            @ApiResponse(code = 404,message = "Aucun commandeClient n'existe dans la DBB avec le code fourni")
    })
    CommandeClientDto findByCode(@PathVariable("codeCommandeClient") String code);
    @GetMapping(value =App_Root + "/commandeClients/all",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Revoie la liste des commandeClients",notes = "cette methode permet de recherche et renvoyer la liste des commandeClients qui exitent dans la BDD ", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La liste des commandeClients/Une liste vide ")

    })
    List<CommandeClientDto> findAll();
    @DeleteMapping(value =App_Root + "/commandeClients/delete/{idCommandeClient}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "suprimer un commandeClient ",notes = "cette methode permet de suprimer un commandeClient  ", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "L'commandeClient a été suprimer ")
    })
    void delete(@PathVariable("idCommandeClient") Integer id);
}
