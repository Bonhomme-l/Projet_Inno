package com.example.petup.ui.home;

import com.example.petup.R;

import java.util.ArrayList;
import java.util.Calendar;

public class Animal {
    public int puce;
    public String prenom;
    public String type;
    public boolean genre;public int img_genre;
    public String race;
    public Calendar naissance;
    public boolean sterelisation;
    public String description;
    public int distance;
    public int photo_profil;
    public ArrayList<Integer> photos;

    public Animal(
            int puce,
            String prenom,
            String type,
            boolean genre,
            String race,
            Calendar naissance,
            boolean sterelisation,
            String description,
            int distance,
            int photo_profil,
            ArrayList<Integer> photos){
        this.puce=puce;
        this.prenom=prenom;
        this.type=type;
        this.genre=genre;
        this.race=race;
        this.naissance=naissance;
        this.sterelisation=sterelisation;
        this.description=description;
        this.distance=distance;
        this.photo_profil=photo_profil;
        this.photos=photos;
        if(genre==true){
            img_genre= R.drawable.ic_female;
        }else{
            img_genre=R.drawable.ic_male;
        }
    }

}
