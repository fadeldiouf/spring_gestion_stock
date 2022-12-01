package com.fadel.gestiondestock.dto;

import com.fadel.gestiondestock.model.LigneVente;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {
    private Integer id;
    private VenteDto vente;
    private Integer idEntreprise;

    private BigDecimal quantite;

    private BigDecimal prixunitaire;
    private ArticleDto article;
    public static LigneVenteDto fromEntity(LigneVente ligneVente){
        if (ligneVente==null){
            return null;
        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                .quantite(ligneVente.getQuantite())
                .prixunitaire(ligneVente.getPrixunitaire())
                .idEntreprise(ligneVente.getIdEntreprise())
                .build();
    }
    public static LigneVente toEntity(LigneVenteDto ligneVenteDto){
        if (ligneVenteDto==null){
            return null;
        }
        LigneVente ligneVente= new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setPrixunitaire(ligneVenteDto.getPrixunitaire());
        ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());
        return ligneVente;
    }
}
