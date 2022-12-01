package com.fadel.gestiondestock.services.impl;

import com.fadel.gestiondestock.dto.CommandeClientDto;
import com.fadel.gestiondestock.dto.LigneCommandeClientDto;
import com.fadel.gestiondestock.exception.EntityNotFoundException;
import com.fadel.gestiondestock.exception.ErrorCodes;
import com.fadel.gestiondestock.exception.InvalidEntityException;
import com.fadel.gestiondestock.model.Article;
import com.fadel.gestiondestock.model.Client;
import com.fadel.gestiondestock.model.CommandeClient;
import com.fadel.gestiondestock.model.LigneCommandeClient;
import com.fadel.gestiondestock.repository.ArticleRepository;
import com.fadel.gestiondestock.repository.ClientRepository;
import com.fadel.gestiondestock.repository.CommandeClientRepository;
import com.fadel.gestiondestock.repository.LigneCommandeClientRepository;
import com.fadel.gestiondestock.services.CommandeClientService;
import com.fadel.gestiondestock.validator.CommandeClientValidator;
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
public class CommandeClientServiceImpl implements CommandeClientService {
    private CommandeClientRepository commandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, ClientRepository clientRepository,
                                     ArticleRepository articleRepository,LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository=ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        List<String> errors = CommandeClientValidator.validate(commandeClientDto);
        if (!errors.isEmpty()){
            log.error("Commande client n'est pas valid{}",commandeClientDto);
            throw new InvalidEntityException("Commande client n'est pas valid", ErrorCodes.COMMANDE_CLIENT_NOT_VALID,errors);
        }
        Optional<Client> client= clientRepository.findById(commandeClientDto.getClient().getId());
        if (client.isEmpty()){
            log.warn("Client ID was not found{}",commandeClientDto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID" +commandeClientDto.getClient().getId()+ "n'a ete trouver dans la BDD",
                    ErrorCodes.CLIENT_NOT_FOUND);

        }
        List<String> errorArticle=new ArrayList<>();
        if (commandeClientDto.getLigneCommandeClients()!=null){
            commandeClientDto.getLigneCommandeClients().forEach(ligneCmdClt ->{
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
        CommandeClient saveCommandeClient= commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));
        if (commandeClientDto.getLigneCommandeClients()!=null){
            commandeClientDto.getLigneCommandeClients().forEach(ligCmdClient->{
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClient);
                ligneCommandeClient.setCommandeClient(saveCommandeClient);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }


        return CommandeClientDto.fromEntity(saveCommandeClient);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id==null){
            log.error("Commande ID is null");
            return null;
        }
       return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucune commande Client avec l'ID" + id +"n'a ete trouver dans la BDD"));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Commande Code is null");
            return null;
        }
        return commandeClientRepository.findCommandeByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("Aucune commande Client avec le Code" + code +"n'a ete trouver dans la BDD"));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Commande ID is null");
            return ;
        }
        commandeClientRepository.deleteById(id);

    }
}
