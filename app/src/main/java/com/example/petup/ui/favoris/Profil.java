package com.example.petup.ui.favoris;

import com.example.petup.ui.home.Animal;

import java.util.ArrayList;
import java.util.Calendar;

abstract class Profil {
    String prenom;
    String nom;
    Calendar naissance;
    String email;
    String mdp;
    public int photo_profil;

    public Profil(String prenom, String nom, Calendar naissance, String email, String mdp, int photo_profil){
        this.prenom=prenom;
        this.nom=nom;
        this.naissance=naissance;
        this.email=email;
        this.mdp=mdp;
        this.photo_profil=photo_profil;
    }
    abstract void ajouterAnimal(Animal a);
    abstract void supprimerAnimal(Animal a);
}
