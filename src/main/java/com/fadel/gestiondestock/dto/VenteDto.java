package com.fadel.gestiondestock.dto;

import com.fadel.gestiondestock.model.Vente;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VenteDto {
    private Integer id;
    private  String code;

    private Instant dateVente;

    private  String commentaires;
    private List<LigneVenteDto> ligneVentes;
    private Integer idEntreprise;
    public static VenteDto fromEntity (Vente vente){
        if (vente==null){
            return null;
        }
        return VenteDto.builder()
                .id(vente.getId())
                .code(vente.getCode())
                .dateVente(vente.getDateVente())
                .commentaires(vente.getCommentaires())
                .idEntreprise(vente.getIdEntreprise())
                .build();
    }
    public static Vente toEntity(VenteDto venteDto){
        if (venteDto==null){
            return null;
        }
        Vente vente= new Vente();
        vente.setId(venteDto.getId());
        vente.setCode(venteDto.getCode());
        vente.setDateVente(venteDto.getDateVente());
        vente.setCommentaires(venteDto.getCommentaires());
        vente.setIdEntreprise(venteDto.getIdEntreprise());
        return vente;
    }
}
