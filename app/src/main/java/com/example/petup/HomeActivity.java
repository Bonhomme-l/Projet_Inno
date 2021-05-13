package com.example.petup;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.petup.ui.home.Animal;
import com.example.petup.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@RequiresApi(api = Build.VERSION_CODES.O)
public class HomeActivity extends AppCompatActivity {
    static ArrayList<Animal> animaux= HomeFragment.animaux;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        intent.getStringExtra("Eleveur");

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_map, R.id.navigation_fav,R.id.navigation_profil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



    }


        @RequiresApi(api = Build.VERSION_CODES.O)
        public static String calculAge(LocalDate naissance){//specify year, month, date directly
            LocalDate ajd = LocalDate.now(); //gets localDate
            Period diff = Period.between(naissance, ajd); //difference between the dates is calculated
            int annee=diff.getYears(); int mois=diff.getYears();int jour=diff.getDays();
            if(annee<=0&&mois>0) return String.valueOf(mois)+" mois";
            else if(mois<=0&&jour>0) return String.valueOf(jour)+" jour";
            else return String.valueOf(annee)+" ans";



        }




    }


