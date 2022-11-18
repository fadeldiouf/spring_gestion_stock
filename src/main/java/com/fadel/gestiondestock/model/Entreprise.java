package com.fadel.gestiondestock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "entreprise")
public class Entreprise extends  AbstractEntity{
    @Column(name = "nom")
    private  String nom;
    @Column(name = "description")
    private  String description ;
    @Column(name = "adresse")
    private  Adresse adresse;
    @Column(name = "email")
    private  String email;
    @Column(name = "photo")
    private  String photo;
    @Column(name = "siteweb")
    private  String siteweb ;
    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;
}
