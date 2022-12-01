package com.fadel.gestiondestock.services.impl;

import com.fadel.gestiondestock.dto.EntrepriseDto;
import com.fadel.gestiondestock.exception.EntityNotFoundException;
import com.fadel.gestiondestock.exception.ErrorCodes;
import com.fadel.gestiondestock.exception.InvalidEntityException;
import com.fadel.gestiondestock.repository.EntrepriseRepository;
import com.fadel.gestiondestock.services.EntrepriseService;
import com.fadel.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {
    private EntrepriseRepository entrepriseRepository;
    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository){
        this.entrepriseRepository=entrepriseRepository;
    }
    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        List<String> errors= EntrepriseValidator.validate(entrepriseDto);
        if (!errors.isEmpty()){
            log.error("Entreprise not valid {}",entrepriseDto);
            throw new InvalidEntityException("entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID,errors);
        }
        return EntrepriseDto.fromEntity(
                entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto))
        );
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id==null){
            log.error("entreprise ID is null");
            return null;
        }
        return entrepriseRepository.findById(id)
                .map(EntrepriseDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("aucune entrepise avec ID=" + id+"n'a ete trouver dans la BDD",ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("entreprise ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);

    }
}
