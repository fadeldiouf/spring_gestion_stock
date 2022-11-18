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

    private BigDecimal quantite;

    private BigDecimal prixunitaire;
    public static LigneVenteDto fromEntity(LigneVente ligneVente){
        if (ligneVente==null){
            return null;
        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .quantite(ligneVente.getQuantite())
                .prixunitaire(ligneVente.getPrixunitaire())
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
        return ligneVente;
    }
}
