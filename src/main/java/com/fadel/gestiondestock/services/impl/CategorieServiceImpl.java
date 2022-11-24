package com.fadel.gestiondestock.services.impl;

import com.fadel.gestiondestock.dto.CategorieDto;
import com.fadel.gestiondestock.exception.EntityNotFoundException;
import com.fadel.gestiondestock.exception.ErrorCodes;
import com.fadel.gestiondestock.exception.InvalidEntityException;
import com.fadel.gestiondestock.repository.CategorieRepository;
import com.fadel.gestiondestock.services.CategorieService;
import com.fadel.gestiondestock.validator.CategorieValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategorieServiceImpl implements CategorieService {
    private CategorieRepository categorieRepository;
    @Autowired
    public CategorieServiceImpl(CategorieRepository categorieRepository){
        this.categorieRepository=categorieRepository;
    }
    @Override
    public CategorieDto save(CategorieDto categorieDto) {
        List<String> errors= CategorieValidator.validate(categorieDto);
        if (!errors.isEmpty()){
            log.error("Article is not valid{}",categorieDto);
            throw new InvalidEntityException("La categorie n'est pas valide", ErrorCodes.CATEGORIE_NOT_VALID);
        }
        return CategorieDto.fromEntity(
                categorieRepository.save(CategorieDto.toEntity(categorieDto))
        );
    }

    @Override
    public CategorieDto findById(Integer id) {
        if (id==null){
            log.error("Categorie ID is null");
            return null;
        }
        return categorieRepository.findById(id)
                .map(CategorieDto::fromEntity)
                .orElseThrow( ()->  new EntityNotFoundException("Auccun categorie avec L'ID=" + id + "n'a ete trouver dans la BDD",ErrorCodes.CATEGORIE_NOT_FOUND));
    }

    @Override
    public CategorieDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Categorie CODE is null");
        }
        return categorieRepository.findArticleByCode(code)
                .map(CategorieDto::fromEntity)
                .orElseThrow( ()->  new EntityNotFoundException("Auccun categorie avec Le CODE=" + code + "n'a ete trouver dans la BDD",ErrorCodes.CATEGORIE_NOT_FOUND));
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieRepository.findAll().stream()
                .map(CategorieDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Categorie ID is null");
            return;
        }
        categorieRepository.deleteById(id);

    }
}
