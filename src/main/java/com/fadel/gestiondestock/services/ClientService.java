package com.fadel.gestiondestock.services;

import com.fadel.gestiondestock.dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto save(ClientDto clientDto);

    ClientDto findById(Integer id);

    List<ClientDto> findAll();

    void delete(Integer id);

    interface CommandeFournisseurService {
    }
}
