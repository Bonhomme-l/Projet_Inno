package com.example.petup.ui.favoris;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.petup.R;
import com.example.petup.ui.home.Animal;
import com.example.petup.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FavorisFragment extends Fragment {
    ArrayList<Animal> animaux= HomeFragment.animaux;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favoris, container, false);
        TextView textView = root.findViewById(R.id.text_dashboard);
        textView.setText("hola");
        return root;
    }

    public ArrayList<ProfilAdopteur> dataProfilAdopteurs(){
        ArrayList<ProfilAdopteur> profilAdopteurs = new ArrayList<>();

        ProfilAdopteur p = new ProfilAdopteur("Laetitia","Kamwag",new GregorianCalendar(2000,4,12),"aurelie.l@outlook.com","depression",R.drawable.ic_dashboard_black_24dp);
        p.ajouterAnimal(animaux.get(0));
        p.ajouterAnimal(animaux.get(1));
        profilAdopteurs.add(p);

        return profilAdopteurs;
    }

    public ArrayList<ProfilAdoptant> dataProfilAdoptants(){
        ArrayList<ProfilAdoptant> profilAdoptants = new ArrayList<>();

        ProfilAdoptant p = new ProfilAdoptant("Sara","Turki",new GregorianCalendar(2000,4,12),"sara.turki@gmail.com","S+U=love",R.drawable.ic_dashboard_black_24dp);
        p.ajouterAnimal(animaux.get(0));
        p.ajouterAnimal(animaux.get(1));
        profilAdoptants.add(p);
        p = new ProfilAdoptant("Clément","Phung",new GregorianCalendar(2000,4,12),"clement.phung@hotmail.fr","ilovelaetitia",R.drawable.ic_dashboard_black_24dp);
        p.ajouterAnimal(animaux.get(6));
        p.ajouterAnimal(animaux.get(1));
        profilAdoptants.add(p);

        return profilAdoptants;
    }
}