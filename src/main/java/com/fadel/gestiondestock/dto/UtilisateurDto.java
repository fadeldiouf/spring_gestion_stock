package com.fadel.gestiondestock.dto;

import com.fadel.gestiondestock.model.Adresse;
import com.fadel.gestiondestock.model.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UtilisateurDto {
    private Integer id;
    private  String nom;
    private  String prenom;
    private  String dateDeNaissance;
    private Adresse adresse;
    private String email;
    private  String motDePasse;
    private  String photo;
    private EntrepriseDto entreprise;
    @JsonIgnore
    private List<RolesDto> roles;

    public  static UtilisateurDto fromEntity(Utilisateur utilisateur){
        if (utilisateur==null){
            return null;
        }
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .dateDeNaissance(utilisateur.getDateDeNaissance())
                .adresse(utilisateur.getAdresse())
                .email(utilisateur.getEmail())
                .motDePasse(utilisateur.getMotDePasse())
                .photo(utilisateur.getPhoto())
                .build();
    }
    public static Utilisateur toEntity(UtilisateurDto utilisateurDto){
        if (utilisateurDto==null){
            return null;
        }
        Utilisateur utilisateur= new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateur.getPrenom());
        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
        utilisateur.setAdresse(utilisateurDto.getAdresse());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        return utilisateur;
    }
}
