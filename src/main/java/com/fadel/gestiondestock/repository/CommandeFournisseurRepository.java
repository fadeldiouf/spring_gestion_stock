package com.fadel.gestiondestock.repository;

import com.fadel.gestiondestock.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur,Integer> {
    Optional<CommandeFournisseur> findCommandeByCode(String code);
}
