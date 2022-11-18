package com.fadel.gestiondestock.validator;

import com.fadel.gestiondestock.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {
    public static List<String> validate(ClientDto clientDto){
        List<String> errors =new ArrayList<>();
        if (clientDto==null){
            errors.add("veuiller renseigner le nom du client");
            errors.add("veuiller renseigner le prenom du client");
            errors.add("veuiller renseigner le mail du client");
            errors.add("veuiller renseigner le numero telephone du client");
            errors.add("veuiller selectionner une photo pour le client");
            return errors;
        }
        if (StringUtils.hasLength(clientDto.getNom())){
                errors.add("veuiller renseigner le nom du client");
            }
        if (StringUtils.hasLength(clientDto.getPrenom())){
            errors.add("veuiller renseigner le prenom du client");
        }
        if (StringUtils.hasLength(clientDto.getEmail())){
            errors.add("veuiller renseigner le mail du client");
        }
        if (clientDto.getAdresse()== null){
            errors.add("veuiller renseigner l'adresse de l'utilisateur");
        }else {

            if (!StringUtils.hasLength(clientDto.getAdresse().getAdresse())){
                errors.add("le champ adresse est obligatoire");
            }
            if (!StringUtils.hasLength(clientDto.getAdresse().getVille())){
                errors.add("le champ ville est obligatoire");
            }
            if (!StringUtils.hasLength(clientDto.getAdresse().getPays())){
                errors.add("le champ pays est obligatoire");
            }
        }
        if (StringUtils.hasLength(clientDto.getNumTel())){
            errors.add("veuiller renseigner le numero telephone du client");
        }
        if (clientDto.getNom()==null){
            errors.add("veuiller selectionner une photo pour le client");
        }
        return  errors;
    }
}
