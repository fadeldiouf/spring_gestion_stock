package com.fadel.gestiondestock.dto;

import com.fadel.gestiondestock.model.LigneCommandeFournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeFournisseurDto {
    private Integer id;
    private ArticleDto article;
    private Integer idEntreprise;
    @JsonIgnore
    private CommandeFournisseurDto commandeFournisseur;
    private BigDecimal quantite;
    private BigDecimal prixunitaire;

    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur){
        if (ligneCommandeFournisseur==null){
            return null;
        }
        return LigneCommandeFournisseurDto.builder()
                .id(ligneCommandeFournisseur.getId())
                .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixunitaire(ligneCommandeFournisseur.getPrixunitaire())
                .idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
                .build();
    }
    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto){
        if (ligneCommandeFournisseurDto==null){
            return null;
        }
        LigneCommandeFournisseur ligneCommandeFournisseur= new LigneCommandeFournisseur();
        ligneCommandeFournisseur.setId(ligneCommandeFournisseurDto.getId());
        ligneCommandeFournisseur.setQuantite(ligneCommandeFournisseurDto.getQuantite());
        ligneCommandeFournisseur.setPrixunitaire(ligneCommandeFournisseurDto.getPrixunitaire());
        ligneCommandeFournisseur.setIdEntreprise(ligneCommandeFournisseurDto.getIdEntreprise());
        return ligneCommandeFournisseur;
    }
}
