package com.example.petup;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.example.petup.ui.home.Animal;
import com.example.petup.ui.home.HomeFragment;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ProfilAnimalActivity  extends AppCompatActivity {
    ViewPager mViewPager;
    RecyclerView recycler;
    ArrayList<Animal> animaux= HomeFragment.dataAnimaux();
    Animal animal;

    TextView nom, ville, adresse, description;

    ViewPagerAdapter mViewPagerAdapter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilanimal);

        nom = findViewById(R.id.prenom_profilanimal);
        ville = findViewById(R.id.ville_profilanimal);
        adresse = findViewById(R.id.adresse_profilanaimal);
        description = findViewById(R.id.description_profilanimal);

        Intent i = getIntent();

        ArrayList<Integer> photos=new ArrayList<Integer>();
        photos.add(R.drawable.rectangle_bleu);
        photos.add(R.drawable.barnavigation_design);
        int photo_profil=i.getIntExtra("Photo Profil",0);
        photos.add(photo_profil);

        nom.setText(i.getStringExtra("Nom"));
        ville.setText(i.getStringExtra("Ville"));
        adresse.setText(i.getStringExtra("Adresse"));
        description.setText(i.getStringExtra("Description"));

        mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);
        mViewPagerAdapter = new ViewPagerAdapter(ProfilAnimalActivity.this, photos);
        mViewPager.setAdapter(mViewPagerAdapter);

        for(Animal a : animaux){
            if(String.valueOf(a.prenom).equals(i.getStringExtra("Nom"))){
                animal=a;
            }
        }

        recycler = findViewById(R.id.recycle_cate_profilanimal);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recycler.setAdapter(new CategoriesAnimauxAdaptater(animal));
    }


}
