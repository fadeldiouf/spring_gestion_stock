package com.fadel.gestiondestock.validator;

import com.fadel.gestiondestock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {
    public static List<String> validate(EntrepriseDto clientDto){
        List<String> errors =new ArrayList<>();
        if (clientDto==null){
            errors.add("veuiller renseigner le nom de l'entreprise");
            errors.add("veuiller renseigner la description de l'entreprise");
            errors.add("veuiller renseigner l'adresse de l'entreprise");
            errors.add("veuiller renseigner le mail de l'entreprise ");
            errors.add("veuiller selectionner une photo pour  l'entreprise");
            return errors;
        }
        if (StringUtils.hasLength(clientDto.getNom())){
            errors.add("veuiller renseigner le nom de l'entreprise");
        }
        if (StringUtils.hasLength(clientDto.getDescription())){
            errors.add("veuiller renseigner la description de l'entreprise");
        }
        if (clientDto.getAdresse()== null){
            errors.add("veuiller renseigner l'adresse de l'entreprise");
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
        if (StringUtils.hasLength(clientDto.getEmail())){
            errors.add("veuiller renseigner le mail de l'entreprise");
        }
        if (clientDto.getNom()==null){
            errors.add("veuiller selectionner une photo pour de l'entreprise");
        }
        return  errors;
    }

}
