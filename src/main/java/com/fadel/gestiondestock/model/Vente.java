package com.fadel.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vente")
public class Vente extends AbstractEntity {
    @Column(name = "code")
    private  String code;
    @Column(name = "datevente")
    private Instant dateVente;
    @Column(name = "commentaires")
    private  String commentaires;
    @Column(name = "identreprise")
    private Integer idEntreprise;


}
