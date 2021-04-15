package com.example.petup.ui.favoris;

import com.example.petup.ui.home.Animal;

import java.util.ArrayList;
import java.util.Calendar;

public class Profil {
    String prenom;
    String nom;
    Calendar naissance;
    ArrayList<Animal> animaux_possédés;

    public Profil(String prenom,String nom,Calendar naissance){
        this.prenom=prenom;
        this.nom=nom;
        this.naissance=naissance;
    }

    public void ajouterAnimal(Animal a){ animaux_possédés.add(a); }
    public void supprimerAnimal(Animal a){
        animaux_possédés.remove(a);
    }

}
