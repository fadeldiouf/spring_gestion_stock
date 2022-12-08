package com.fadel.gestiondestock.controller.api;

import com.fadel.gestiondestock.dto.ClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fadel.gestiondestock.utils.Contants.App_Root;

@Api(App_Root +"/client")
public interface ClientApi {
    @PostMapping(value =App_Root + "/clients/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Enregister un client",notes = "cette methode permet d'enregister ou modifier un client", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Client cree/modifier"),
            @ApiResponse(code = 400,message = "Client n'est pas valide")
    })
    ClientDto save(@RequestBody ClientDto clientDto);

    @GetMapping(value =App_Root + "/clients/{idClient}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher un client par ID ",notes = "cette methode permet de rechercher un client par son ID ", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Le client a été trouvée dans la BDD "),
            @ApiResponse(code = 404,message = "Aucun client n'existe dans la DBB avec l'ID fourni")
    })
    ClientDto findById(@PathVariable("idCategorie")Integer id);

    @GetMapping(value =App_Root + "/clients/all",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Revoie la liste des clients",notes = "cette methode permet de recherche et renvoyer la liste des clients qui exitent dans la BDD ", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La liste des clients/Une liste vide ")

    })
    List<ClientDto> findAll();

    @DeleteMapping(value =App_Root + "/clients/delete/{idClient}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "suprimer un client ",notes = "cette methode permet de suprimer un client  ", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Le client a été suprimer ")
    })
    void delete(@PathVariable ("idClient")Integer id);
}
