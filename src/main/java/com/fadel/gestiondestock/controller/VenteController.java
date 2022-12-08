package com.fadel.gestiondestock.controller;

import com.fadel.gestiondestock.controller.api.VenteApi;
import com.fadel.gestiondestock.dto.VenteDto;
import com.fadel.gestiondestock.services.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class VenteController implements VenteApi {
    private VenteService venteService;
    @Autowired
    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @Override
    public VenteDto save(VenteDto venteDto) {
        return venteService.save(venteDto);
    }

    @Override
    public VenteDto findById(Integer id) {
        return venteService.findById(id);
    }

    @Override
    public VenteDto findByCode(String code) {
        return venteService.findByCode(code);
    }

    @Override
    public List<VenteDto> findAll() {
        return venteService.findAll();
    }

    @Override
    public void delete(Integer id) {
        venteService.delete(id);

    }
}
