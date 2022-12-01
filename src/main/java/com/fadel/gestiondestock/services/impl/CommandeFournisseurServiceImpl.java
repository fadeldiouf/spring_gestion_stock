package com.fadel.gestiondestock.services.impl;

import com.fadel.gestiondestock.dto.CommandeFournisseurDto;
import com.fadel.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.fadel.gestiondestock.exception.EntityNotFoundException;
import com.fadel.gestiondestock.exception.ErrorCodes;
import com.fadel.gestiondestock.exception.InvalidEntityException;
import com.fadel.gestiondestock.model.Article;
import com.fadel.gestiondestock.model.Fournisseur;
import com.fadel.gestiondestock.model.CommandeFournisseur;
import com.fadel.gestiondestock.model.LigneCommandeFournisseur;
import com.fadel.gestiondestock.repository.ArticleRepository;
import com.fadel.gestiondestock.repository.FournisseurRepository;
import com.fadel.gestiondestock.repository.CommandeFournisseurRepository;
import com.fadel.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.fadel.gestiondestock.services.CommandeFournisseurService;
import com.fadel.gestiondestock.validator.CommandeFournisseurValidator;
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
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {
    private CommandeFournisseurRepository commandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository, FournisseurRepository fournisseurRepository,
                                     ArticleRepository articleRepository,LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeFournisseurRepository=ligneCommandeFournisseurRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {
        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDto);
        if (!errors.isEmpty()){
            log.error("Commande fournisseur n'est pas valid{}",commandeFournisseurDto);
            throw new InvalidEntityException("Commande fournisseur n'est pas valid", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID,errors);
        }
        Optional<Fournisseur> fournisseur= fournisseurRepository.findById(commandeFournisseurDto.getFournisseur().getId());
        if (fournisseur.isEmpty()){
            log.warn("Fournisseur ID was not found{}",commandeFournisseurDto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun fournisseur avec l'ID" +commandeFournisseurDto.getFournisseur().getId()+ "n'a ete trouver dans la BDD",
                    ErrorCodes.FOURNISSEUR_NOT_FOUND);

        }
        List<String> errorArticle=new ArrayList<>();
        if (commandeFournisseurDto.getLigneCommandeFournisseurs()!=null){
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(ligneCmdClt ->{
                if (ligneCmdClt.getArticle()!=null){
                    Optional<Article> article= articleRepository.findById(ligneCmdClt.getArticle().getId());
                    if (article.isEmpty()){
                        errorArticle.add("Aucun article avec l'ID" + ligneCmdClt.getArticle().getId()+"n'a ete trouver dans la BDD ");
                    }else {
                        errorArticle.add("Imposible d'enregister une commande avec un aricle null ");
                    }
                }

            } );
        }
        if (!errorArticle.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("L'article n'existe pas dans la BDD",ErrorCodes.ARTICLE_NOT_FOUND,errorArticle);
        }
        CommandeFournisseur saveCommandeFournisseur= commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));
        if (commandeFournisseurDto.getLigneCommandeFournisseurs()!=null){
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(ligCmdFournisseur->{
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmdFournisseur);
                ligneCommandeFournisseur.setCommandeFournisseur(saveCommandeFournisseur);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }


        return CommandeFournisseurDto.fromEntity(saveCommandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (id==null){
            log.error("Commande ID is null");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucune commande Fournisseur avec l'ID" + id +"n'a ete trouver dans la BDD",ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Commande Code is null");
            return null;
        }
        return commandeFournisseurRepository.findCommandeByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucune commande Fournisseur avec le Code" + code +"n'a ete trouver dans la BDD",ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Commande ID is null");
            return ;
        }
        commandeFournisseurRepository.deleteById(id);

    }
}
