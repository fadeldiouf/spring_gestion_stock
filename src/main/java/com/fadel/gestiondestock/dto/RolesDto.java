package com.fadel.gestiondestock.dto;
import com.fadel.gestiondestock.model.Roles;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class RolesDto {
    private Integer id;
    private String nomrole;

    private UtilisateurDto utilisateur;

    public static  RolesDto fromEntity(Roles roles){
        if (roles==null){
            return null;
        }
        return RolesDto.builder()
                .id(roles.getId())
                .nomrole(roles.getNomrole())
                .build() ;
    }
    public static Roles toEntity(RolesDto rolesDto){
        if (rolesDto==null){
            return null;
        }
        Roles roles= new Roles();
        roles.setId(rolesDto.getId());
        roles.setNomrole(rolesDto.getNomrole());
        return roles;
    }
}
