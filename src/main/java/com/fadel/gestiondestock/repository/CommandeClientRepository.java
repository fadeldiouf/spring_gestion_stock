package com.fadel.gestiondestock.repository;

import com.fadel.gestiondestock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient,Integer> {
    Optional<CommandeClient> findCommandeByCode(String Code);
}
