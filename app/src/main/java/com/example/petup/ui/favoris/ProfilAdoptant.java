package com.example.petup.ui.favoris;

import com.example.petup.ui.home.Animal;

import java.util.ArrayList;
import java.util.Calendar;

public class ProfilAdoptant extends Profil {
    ArrayList<Animal> animaux_likés;

    public ProfilAdoptant(String prenom, String nom, Calendar naissance, String email, String mdp,int photo_profil){
        super(prenom, nom, naissance, email, mdp,photo_profil);
    }

    void ajouterAnimal(Animal a){ animaux_likés.add(a); }
    void supprimerAnimal(Animal a){
        animaux_likés.remove(a);
    }

}

