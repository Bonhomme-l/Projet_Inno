package com.example.petup.ui.home;

import com.example.petup.R;

import java.util.ArrayList;

public class Animal {
    public String prenom;
    public String type;
    public boolean sexe;public int img_sexe;
    public String race;
    public String description;
    public int distance;
    public int photo_profil;
    public ArrayList<Integer> photos;

    public Animal( String prenom,
                   String type,
                   boolean sexe,
                   String race,
                   String description,
                   int distance,
                   int photo_profil,
                   ArrayList<Integer> photos){
        this.prenom=prenom;
        this.type=type;
        this.sexe=sexe;
        this.race=race;
        this.description=description;
        this.distance=distance;
        this.photo_profil=photo_profil;
        this.photos=photos;
        if(sexe==true){
            img_sexe= R.drawable.ic_female;
        }else{
            img_sexe=R.drawable.ic_male;
        }
    }





}
