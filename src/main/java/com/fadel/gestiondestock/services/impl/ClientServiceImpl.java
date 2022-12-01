package com.fadel.gestiondestock.services.impl;

import com.fadel.gestiondestock.dto.ClientDto;
import com.fadel.gestiondestock.exception.EntityNotFoundException;
import com.fadel.gestiondestock.exception.ErrorCodes;
import com.fadel.gestiondestock.exception.InvalidEntityException;
import com.fadel.gestiondestock.repository.ClientRepository;
import com.fadel.gestiondestock.services.ClientService;
import com.fadel.gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository=clientRepository;
    }
    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String> errors= ClientValidator.validate(clientDto);
        if (!errors.isEmpty()){
            log.error("client is not valid{}",clientDto);
            throw new InvalidEntityException("le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID,errors);
        }
        return ClientDto.fromEntity(
                clientRepository.save(ClientDto.toEntity(clientDto))
        );
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id==null){
            log.error("Client ID is null");
            return null;
        }
        return clientRepository.findById(id)
                .map(ClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucun client avec l'ID" +id+ "n'a ete trouver dans la BDD",
                        ErrorCodes.CLIENT_NOT_FOUND));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Client ID is null");
            return;
        }
        clientRepository.deleteById(id);


    }
}
