package com.example.petup.ui.home;

import com.example.petup.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class Animal {
    public String ville;
    public String prenom;
    public String type;
    public boolean genre;public int img_genre;
    public String race;
    public LocalDate naissance;
    public boolean sterelisation;
    public String description;
    public int distance;
    public String adresse;
    public int taille;
    public String pelage;
    public String yeux;
    public boolean exterieur;

    public int photo_profil;
    public ArrayList<Integer> photos;

    public Animal(
            String prenom,
            String type,
            boolean genre,
            String race,
            LocalDate naissance,
            boolean sterelisation,
            String description,
            int distance,
            String adresse,
            String ville,
            String pelage,
            String yeux,
            int taille,
            boolean exterieur,
            int photo_profil, ArrayList<Integer> photos){
        this.prenom=prenom;
        this.type=type;
        this.genre=genre;
        this.race=race;
        this.naissance=naissance;
        this.sterelisation=sterelisation;
        this.description=description;
        this.distance=distance;
        this.adresse=adresse;
        this.ville=ville;
        this.pelage=pelage;
        this.taille=taille;
        this.yeux=yeux;
        this.exterieur=exterieur;
        this.photo_profil=photo_profil;
        this.photos=photos;
        if(genre==true){
            img_genre= R.drawable.female;
        }else{
            img_genre=R.drawable.male;
        }
    }

    public void ajouterPhoto(int i){
        photos.add(i);
    }
    public void supprimerPhoto(int i){
        photos.add(i);
    }


}
