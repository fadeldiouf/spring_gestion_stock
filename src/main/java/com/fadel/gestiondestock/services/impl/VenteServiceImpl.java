package com.fadel.gestiondestock.services.impl;

import com.fadel.gestiondestock.dto.CommandeClientDto;
import com.fadel.gestiondestock.dto.LigneVenteDto;
import com.fadel.gestiondestock.dto.VenteDto;
import com.fadel.gestiondestock.exception.EntityNotFoundException;
import com.fadel.gestiondestock.exception.ErrorCodes;
import com.fadel.gestiondestock.exception.InvalidEntityException;
import com.fadel.gestiondestock.model.Article;
import com.fadel.gestiondestock.model.LigneVente;
import com.fadel.gestiondestock.model.Vente;
import com.fadel.gestiondestock.repository.ArticleRepository;
import com.fadel.gestiondestock.repository.LigneVenteRepository;
import com.fadel.gestiondestock.repository.VenteRepository;
import com.fadel.gestiondestock.services.VenteService;
import com.fadel.gestiondestock.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService {
    private VenteRepository venteRepository;
    private ArticleRepository articleRepository;
    private LigneVenteRepository ligneVenteRepository;
    @Autowired
    public VenteServiceImpl(VenteRepository venteRepository, ArticleRepository articleRepository, LigneVenteRepository ligneVenteRepository) {
        this.venteRepository = venteRepository;
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VenteDto save(VenteDto venteDto) {
        List<String> errors = VenteValidator.validate(venteDto);
        if(!errors.isEmpty()){
            log.error("vente n'est pa valide");
            throw new InvalidEntityException("Vente invalide", ErrorCodes.VENTE_NOT_VALID,errors);
        }
        List<String> errorArticle= new ArrayList<>();
        venteDto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (article.isEmpty()) {
                errorArticle.add("Aucun article avec l'ID" + ligneVenteDto.getArticle().getId() + "n'a ete trouver dans la BDD ");
            }
        });
        if (!errorArticle.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("L'article n'existe pas dans la BDD",ErrorCodes.ARTICLE_NOT_FOUND,errorArticle);
        }
        Vente savevente =venteRepository.save(VenteDto.toEntity(venteDto));
        venteDto.getLigneVentes().forEach(ligneVenteDto ->{
            LigneVente  ligneVente= LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savevente);
            ligneVenteRepository.save(ligneVente);
        });


        return VenteDto.fromEntity(savevente);
    }

    @Override
    public VenteDto findById(Integer id) {
        if (id==null){
            log.error("Vente ID is null");
            return null;
        }
        return venteRepository.findById(id)
                .map(VenteDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucune vente avec l'ID" + id +"n'a ete trouver dans la BDD"));
    }

    @Override
    public VenteDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Commande Code is null");
            return null;
        }
        return venteRepository.findVenteByCode(code)
                .map(VenteDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucune vente avec le Code" + code +"n'a ete trouver dans la BDD"));
    }
    @Override
    public List<VenteDto> findAll() {
        return venteRepository.findAll().stream()
                .map(VenteDto::fromEntity).collect(
                        Collectors.toList()
                );
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Vente ID is null");
            return ;
        }
        venteRepository.deleteById(id);
    }
}
