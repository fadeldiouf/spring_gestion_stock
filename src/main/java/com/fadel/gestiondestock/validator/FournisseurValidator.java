package com.fadel.gestiondestock.validator;

import com.fadel.gestiondestock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {
    public static List<String> validate(FournisseurDto fournisseurDto){
        List<String> errors =new ArrayList<>();
        if (fournisseurDto==null){
            errors.add("veuiller renseigner le nom du fournisseur");
            errors.add("veuiller renseigner le prenom du fournisseur");
            errors.add("veuiller renseigner le mail du fournisseur");
            errors.add("veuiller renseigner le numero telephone du fournisseur");
            errors.add("veuiller selectionner une photo pour le fournisseur");
            return errors;
        }
        if (StringUtils.hasLength(fournisseurDto.getNom())){
            errors.add("veuiller renseigner le nom du fournisseur");
        }
        if (StringUtils.hasLength(fournisseurDto.getPrenom())){
            errors.add("veuiller renseigner le prenom du fournisseur");
        }
        if (StringUtils.hasLength(fournisseurDto.getEmail())){
            errors.add("veuiller renseigner le mail du fournisseur");
        }
        if (fournisseurDto.getAdresse()== null){
            errors.add("veuiller renseigner l'adresse de l'utilisateur");
        }else {

            if (!StringUtils.hasLength(fournisseurDto.getAdresse().getAdresse())){
                errors.add("le champ adresse est obligatoire");
            }
            if (!StringUtils.hasLength(fournisseurDto.getAdresse().getVille())){
                errors.add("le champ ville est obligatoire");
            }
            if (!StringUtils.hasLength(fournisseurDto.getAdresse().getPays())){
                errors.add("le champ pays est obligatoire");
            }
        }
        if (StringUtils.hasLength(fournisseurDto.getNumTel())){
            errors.add("veuiller renseigner le numero telephone du fournisseur");
        }
        if (fournisseurDto.getNom()==null){
            errors.add("veuiller selectionner une photo pour le fournisseur");
        }
        return  errors;
    }
}
