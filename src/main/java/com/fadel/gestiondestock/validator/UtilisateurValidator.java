package com.fadel.gestiondestock.validator;

import com.fadel.gestiondestock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {
    public static List<String> validate(UtilisateurDto utilisateurDto){
        List<String> errors= new ArrayList<>();
        if (utilisateurDto == null){
            errors.add("veuiller renseigner le nom de l'utilisateur");
            errors.add("veuiller renseigner le prénom de l'utilisateur");
            errors.add("veuiller renseigner l'email de l'utilisateur");
            errors.add("veuiller renseigner la date de naissance  de l'utilisateur");
            errors.add("veuiller renseigner l'adresse de l'utilisateur");
            errors.add("veuiller renseigner le mot de passe de l'utilisateur");
            errors.add("veuiller selectionner une  photo ");
            return errors;
        }
        if (!StringUtils.hasLength(utilisateurDto.getNom())){
            errors.add("veuiller renseigner le nom de l'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getPrenom())){
            errors.add("veuiller renseigner le prénom de l'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getEmail())){
            errors.add("veuiller renseigner l'email de l'utilisateur");
        }
        if (utilisateurDto.getDateDeNaissance()==null){
            errors.add("veuiller renseigner la date de naissance  de l'utilisateur");
        }
        if (utilisateurDto.getAdresse()== null){
            errors.add("veuiller renseigner l'adresse de l'utilisateur");
        }else {

            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse())){
                errors.add("le champ adresse est obligatoire");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getVille())){
                errors.add("le champ ville est obligatoire");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getPays())){
                errors.add("le champ pays est obligatoire");
            }
        }
        if (!StringUtils.hasLength(utilisateurDto.getMotDePasse())){
            errors.add("veuiller renseigner le mot de passe de l'utilisateur");
        }
        if (utilisateurDto.getPhoto()==null){
            errors.add("\"veuiller selectionner une  photo");
        }


        return  errors;
    }
}
