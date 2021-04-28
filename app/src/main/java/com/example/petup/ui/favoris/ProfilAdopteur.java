package com.example.petup.ui.favoris;


import com.example.petup.ui.home.Animal;

import java.util.ArrayList;
import java.util.Calendar;

public class ProfilAdopteur extends Profil {
    ArrayList<Animal> animaux_possédés;

    public ProfilAdopteur(String prenom, String nom, Calendar naissance, String email, String mdp,int photo_profil){
        super(prenom, nom, naissance, email, mdp,photo_profil);
    }

    void ajouterAnimal(Animal a){ animaux_possédés.add(a); }
    void supprimerAnimal(Animal a){ animaux_possédés.remove(a); }

}
