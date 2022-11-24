package com.fadel.gestiondestock.services.impl;

import com.fadel.gestiondestock.dto.FournisseurDto;
import com.fadel.gestiondestock.exception.EntityNotFoundException;
import com.fadel.gestiondestock.exception.ErrorCodes;
import com.fadel.gestiondestock.exception.InvalidEntityException;
import com.fadel.gestiondestock.repository.FournisseurRepository;
import com.fadel.gestiondestock.services.FournisseurService;
import com.fadel.gestiondestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {
    private FournisseurRepository fournisseurRepository;
    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository){
        this.fournisseurRepository=fournisseurRepository;
    }
    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        List<String> errors= FournisseurValidator.validate(fournisseurDto);
        if (!errors.isEmpty()){
            log.error("fournisseur not valid {}",fournisseurDto);
            throw new InvalidEntityException("le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID);

        }
        return FournisseurDto.fromEntity(
                fournisseurRepository.save(FournisseurDto.toEntity(fournisseurDto))
        );
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if (id==null){
            log.error("fournisseur ID is null");
            return null;
        }
        return fournisseurRepository.findById(id)
                .map(FournisseurDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("aucun fournisseur avec l'ID=" + id + "n'a ete trouver dans la BDD",ErrorCodes.FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id==null){
            log.error("fournisseur ID is null");
            return;
        }
        fournisseurRepository.findById(id);
    }
}
