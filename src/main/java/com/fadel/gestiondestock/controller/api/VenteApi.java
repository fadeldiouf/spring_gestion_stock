package com.fadel.gestiondestock.controller.api;

import com.fadel.gestiondestock.dto.VenteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fadel.gestiondestock.utils.Contants.App_Root;
@Api(App_Root +"/vente")
public interface VenteApi {
    @PostMapping(value =App_Root + "/ventes/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Enregister une vente",notes = "cette methode permet d'enregister ou modifier une vente", response = VenteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Vente cree/modifier"),
            @ApiResponse(code = 400,message = "Vente n'est pas valide")
    })
    VenteDto save(@RequestBody VenteDto venteDto);
    @GetMapping(value =App_Root + "/ventes/{idVente}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher une vente par ID ",notes = "cette methode permet de rechercher une vente par son ID ", response = VenteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La vente a été trouvée dans la BDD "),
            @ApiResponse(code = 404,message = "Aucune vente n'existe dans la DBB avec l'ID fourni")
    })
    VenteDto findById(@PathVariable("idVente") Integer id);
    @GetMapping(value =App_Root + "/ventes/vente/{codeVente}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Rechercher un vente  par code",notes = "cette methode permet de rechercher une vente par son code ", response = VenteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La vente a été trouvée dans la BDD "),
            @ApiResponse(code = 404,message = "Aucune vente n'existe dans la DBB avec le code fourni")
    })
    VenteDto findByCode(@PathVariable("codeVente") String code);
    @GetMapping(value =App_Root + "/ventes/all",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "Revoie la liste des ventes",notes = "cette methode permet de recherche et renvoyer la liste des ventes qui exitent dans la BDD ", response = VenteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La liste des ventes/Une liste vide ")

    })
    List<VenteDto> findAll();
    @DeleteMapping(value =App_Root + "/ventes/delete/{idVente}",produces =MediaType.APPLICATION_JSON_VALUE )
    @ApiOperation(value = "suprimer une vente ",notes = "cette methode permet de suprimer une vente  ", response = VenteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "La vente a été suprimer ")
    })
    void delete(@PathVariable("idVente") Integer id);
}
