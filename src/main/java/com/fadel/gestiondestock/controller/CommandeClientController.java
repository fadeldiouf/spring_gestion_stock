package com.fadel.gestiondestock.controller;

import com.fadel.gestiondestock.controller.api.CommandeClientApi;
import com.fadel.gestiondestock.dto.CommandeClientDto;
import com.fadel.gestiondestock.services.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CommandeClientController implements CommandeClientApi {

    private CommandeClientService commandeClientService;

    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        return commandeClientService.save(commandeClientDto);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        return commandeClientService.findById(id);
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        return commandeClientService.findByCode(code);
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientService.findAll();
    }

    @Override
    public void delete(Integer id) {
        commandeClientService.delete(id);

    }
}
