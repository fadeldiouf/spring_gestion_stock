package com.fadel.gestiondestock.dto;

import com.fadel.gestiondestock.model.Adresse;
import com.fadel.gestiondestock.model.Fournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class FournisseurDto {
    private Integer id;
    private String nom;

    private String prenom;

    private Adresse adresse;

    private String photo;

    private String email;

    private String numTel;
    private Integer idEntreprise;
    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurs;

    public static FournisseurDto fromEntity(Fournisseur fournisseur){
        if (fournisseur == null){
            return null;
        }
        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .adresse(fournisseur.getAdresse())
                .photo(fournisseur.getPhoto())
                .email(fournisseur.getPrenom())
                .numTel(fournisseur.getNumTel())
                .idEntreprise(fournisseur.getIdEntreprise())
                .build();
    }
    public static Fournisseur toEntity(FournisseurDto fournisseurDto){
        if (fournisseurDto==null){
            return null;
        }

        Fournisseur fournisseur=new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setAdresse(fournisseurDto.getAdresse());
        fournisseur.setPhoto(fournisseurDto.getPhoto());
        fournisseur.setNumTel(fournisseurDto.getNumTel());
        fournisseur.setIdEntreprise(fournisseurDto.getIdEntreprise());
        return fournisseur;
    }

}
