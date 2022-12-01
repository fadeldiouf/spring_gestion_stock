package com.fadel.gestiondestock.services;

import com.fadel.gestiondestock.dto.ClientDto;
import com.fadel.gestiondestock.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto commandeClientDto);

    CommandeClientDto findById(Integer id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll();

    void delete(Integer id);
}
