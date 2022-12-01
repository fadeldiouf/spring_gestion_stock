package com.fadel.gestiondestock.services.impl;

import com.fadel.gestiondestock.dto.UtilisateurDto;
import com.fadel.gestiondestock.exception.EntityNotFoundException;
import com.fadel.gestiondestock.exception.ErrorCodes;
import com.fadel.gestiondestock.exception.InvalidEntityException;
import com.fadel.gestiondestock.repository.UtilisateurRepository;
import com.fadel.gestiondestock.services.UtilisateurService;
import com.fadel.gestiondestock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository=utilisateurRepository;
    }
    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        List<String> errors = UtilisateurValidator.validate(utilisateurDto);
        if (!errors.isEmpty()){
            log.error("utilisateur not valid {}",utilisateurDto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID,errors);

        }

        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto)));
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id==null){
            log.error("utilisateur ID is null");
            return null;
        }
        return utilisateurRepository.findById(id)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucune utilisateur avec ID=" +id + "n'a ete trouver dans la BDD",ErrorCodes.UTILISATEUR_NOT_FOUND));
    }
    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("utilisateur ID is null");
            return;
        }
        utilisateurRepository.deleteById(id);

    }
}
