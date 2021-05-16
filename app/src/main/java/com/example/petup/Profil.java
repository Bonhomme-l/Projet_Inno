package com.example.petup;

import java.util.ArrayList;

public class Profil {
    public ArrayList<String> animaux;
    public String naissance;
    public boolean eleveur;
    public String nom;
    public String pp_url;
    public String prenom;
    public String sexe;
    public String ville;
    public String adresse;



    public Profil(String adresse, ArrayList<String> animaux, String naissance, boolean eleveur, String nom, String pp_url, String prenom, String sexe, String ville) {
        this.adresse = adresse;
        this.animaux = animaux;
        this.naissance = naissance;
        this.eleveur = eleveur;
        this.nom = nom;
        this.pp_url = pp_url;
        this.prenom = prenom;
        this.sexe = sexe;
        this.ville = ville;
    }

    public Profil(){}

    public ArrayList<String> getAnimaux() {
        return animaux;
    }

    public String getNaissance() {
        return naissance;
    }

    public boolean isEleveur() {
        return eleveur;
    }

    public String getNom() {
        return nom;
    }

    public String getPp_url() {
        return pp_url;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public String getVille() {
        return ville;
    }

    public String getAdresse() {
        return adresse;
    }
}
