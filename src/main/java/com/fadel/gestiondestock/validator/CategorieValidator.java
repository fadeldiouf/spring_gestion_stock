package com.fadel.gestiondestock.validator;

import com.fadel.gestiondestock.dto.CategorieDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategorieValidator {
    public static List<String> validate(CategorieDto categorieDto){
        List<String> errors = new ArrayList<>();
        if(categorieDto==null || !StringUtils.hasLength(categorieDto.getCode())){
            errors.add("veuillerr renseigner le code de la categorie");

        }

        return errors;
    }
}
