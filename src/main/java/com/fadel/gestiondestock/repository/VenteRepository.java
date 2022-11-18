package com.fadel.gestiondestock.repository;

import com.fadel.gestiondestock.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Vente,Integer> {
}
