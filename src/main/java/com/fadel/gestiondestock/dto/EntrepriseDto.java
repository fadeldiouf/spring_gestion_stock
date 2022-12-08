package com.fadel.gestiondestock.dto;
import com.fadel.gestiondestock.model.Adresse;
import com.fadel.gestiondestock.model.Entreprise;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class EntrepriseDto {
    private Integer id;
    private  String nom;

    private  String description ;

    private Adresse adresse;

    private  String email;

    private  String photo;

    private  String siteweb ;
    @JsonIgnore
    private List<UtilisateurDto> utilisateurs;
    public static EntrepriseDto fromEntity(Entreprise entreprise){
        if (entreprise==null){
            return null;
        }
        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
                .adresse(entreprise.getAdresse())
                .email(entreprise.getEmail())
                .photo(entreprise.getPhoto())
                .siteweb(entreprise.getSiteweb())
                .build();
    }
    public static Entreprise toEntity(EntrepriseDto entrepriseDto){
        if (entrepriseDto== null){
            return null;
        }
        Entreprise entreprise=new Entreprise();
        entreprise.setId(entrepriseDto.getId());
        entreprise.setNom(entrepriseDto.getNom());
        entreprise.setDescription(entrepriseDto.getDescription());
        entreprise.setAdresse(entrepriseDto.getAdresse());
        entreprise.setEmail(entrepriseDto.getEmail());
        entreprise.setPhoto(entrepriseDto.getPhoto());
        entreprise.setSiteweb(entrepriseDto.getSiteweb());
       return entreprise;
    }
}
