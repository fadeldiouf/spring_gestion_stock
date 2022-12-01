package com.fadel.gestiondestock.repository;

import com.fadel.gestiondestock.model.Vente;
import com.fadel.gestiondestock.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenteRepository extends JpaRepository<Vente,Integer> {
    Optional<Vente> findVenteByCode(String Code);
}
