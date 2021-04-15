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

import static com.example.petup.ui.home.HomeFragment.*;

public class FavorisFragment extends Fragment {
    ArrayList<Animal> animaux= HomeFragment.dataAnimaux();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favoris, container, false);
        TextView textView = root.findViewById(R.id.text_dashboard);
        textView.setText("hola");
        return root;
    }

    public ArrayList<Profil> dataProfil(){
        ArrayList<Profil> profils = new ArrayList<>();

        Profil p = new Profil("Sara","Turki",new GregorianCalendar(2000,4,12));
        profils.add(p);

        return profils;
    }
}