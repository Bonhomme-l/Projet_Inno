package com.example.petup;

import com.example.petup.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class Animal {
    public String naissance;
    public String description;
    public boolean exterieur;
    public boolean genre;
    public String idProfil;
    public String pelage;
    public String prenom;
    public String race;
    public boolean reproduction;
    public boolean sterelisation;
    public String taille;
    public String type;
    public String yeux;
    public String pp_url;
    public String ville;
    public String id;




    public Animal(
            String prenom,
            String type,
            boolean genre,
            String race,
            String naissance,
            boolean sterelisation,
            String description,
            String pelage,
            String yeux,
            String taille,
            boolean exterieur,
            boolean reproduction,
            String idProfil,
            String pp_url,
            String ville){
        this.prenom=prenom;
        this.type=type;
        this.genre=genre;
        this.race=race;
        this.naissance=naissance;
        this.sterelisation=sterelisation;
        this.description=description;
        this.pelage=pelage;
        this.taille=taille;
        this.yeux=yeux;
        this.exterieur=exterieur;
        this.reproduction=reproduction;
        this.genre=genre;
        this.idProfil=idProfil;
        this.pp_url=pp_url;
        this.ville=ville;
    }

    public Animal(){}

    public String getId() {
        return id;
    }

    public String getVille() {
        return ville;
    }

    public String getNaissance() {
        return naissance;
    }

    public String getDescription() {
        return description;
    }

    public boolean isExterieur() {
        return exterieur;
    }

    public boolean isGenre() {
        return genre;
    }

    public String getIdProfil() {
        return idProfil;
    }

    public String getPelage() {
        return pelage;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getRace() {
        return race;
    }

    public boolean isReproduction() {
        return reproduction;
    }

    public boolean isSterelisation() {
        return sterelisation;
    }

    public String getTaille() {
        return taille;
    }

    public String getType() {
        return type;
    }

    public String getYeux() {
        return yeux;
    }

    public String getPp_url() {
        return pp_url;
    }
}
