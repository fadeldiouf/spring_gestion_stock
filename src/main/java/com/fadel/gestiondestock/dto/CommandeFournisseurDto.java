package com.fadel.gestiondestock.dto;


import com.fadel.gestiondestock.model.CommandeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeFournisseurDto {
    private Integer id;
    private  String code;
    private Instant date;
    private Integer idEntreprise;

    private FournisseurDto fournisseur;
    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur){
        if (commandeFournisseur==null){
            return null;
        }
       return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
               .date(commandeFournisseur.getDate())
               .idEntreprise(commandeFournisseur.getIdEntreprise())
               .fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
                .build();
    }
    public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto){
        if (commandeFournisseurDto==null){
            return null;
        }
        CommandeFournisseur commandeFournisseur= new CommandeFournisseur();
        commandeFournisseur.setId(commandeFournisseurDto.getId());
        commandeFournisseur.setCode(commandeFournisseurDto.getCode());
        commandeFournisseur.setDate(commandeFournisseurDto.getDate());
        commandeFournisseur.setIdEntreprise(commandeFournisseurDto.getIdEntreprise());
        return commandeFournisseur;
    }
}
