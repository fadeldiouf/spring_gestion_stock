package com.fadel.gestiondestock.dto;
import com.fadel.gestiondestock.model.Adresse;
import com.fadel.gestiondestock.model.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto {
    private Integer id;

    private String nom;

    private String prenom;
    private Adresse adresse;


    private String photo;

    private String email;

    private String numTel;
    @JsonIgnore
    private List<CommandeClientDto> commandeClients;

    public static ClientDto fromEntity(Client client){
        if(client== null){
            return null;
        }
      return   ClientDto.builder()
              .id(client.getId())
              .nom(client.getNom())
              .prenom(client.getPrenom())
              .adresse(client.getAdresse())
              .photo(client.getPhoto())
              .email(client.getEmail())
              .numTel(client.getNumTel())
              .build();

    }
    public static Client toEntity(ClientDto clientDto){
        if (clientDto==null){
            return null;
        }
        Client client= new Client();
        client.setId(clientDto.getId());
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setAdresse(clientDto.getAdresse());
        client.setPhoto(clientDto.getPhoto());
        client.setEmail(clientDto.getEmail());
        client.setNumTel(clientDto.getNumTel());
        return client;
    }
}
