package com.example.petup;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.petup.ui.favoris.FavorisFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class HomeActivity extends AppCompatActivity {
    public static ArrayList<Animal> animaux_adopte = new ArrayList<>();
    public static ArrayList<Animal> animaux_reproduction = new ArrayList<>();
    public static ArrayList<Animal> animaux;
    public static boolean el;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        el=intent.getBooleanExtra("Eleveur",false);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_map, R.id.navigation_fav,R.id.navigation_profil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        updateAnimaux();

        if(el) animaux = new ArrayList<>(animaux_reproduction);
        else animaux = new ArrayList<>(animaux_adopte);

    }
    public void updateAnimaux(){
        animaux_adopte.clear();animaux_reproduction.clear();
        db.collection("Animaux").whereEqualTo("reproduction",true).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        animaux_reproduction.add(document.toObject(Animal.class));
                    }
                }
            }
        });


        db.collection("Animaux").whereEqualTo("reproduction",false).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Animal> a = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        a.add(document.toObject(Animal.class));
                    }
                    refresh(a);
                }
            }
        });
    }

    public void refresh(ArrayList<Animal> a){
        animaux_adopte.clear();animaux_adopte.addAll(a);
    }

    public static void refresh2(ArrayList<String> a){
        FavorisFragment.animaux.clear();FavorisFragment.animaux.addAll(a);
    }


        @RequiresApi(api = Build.VERSION_CODES.O)
        public static String calculAge(String date){//specify year, month, date directly
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate naissance = LocalDate.parse(date, formatter);
            LocalDate ajd = LocalDate.now(); //gets localDate
            Period diff = Period.between(naissance, ajd); //difference between the dates is calculated
            int annee=diff.getYears(); int mois=diff.getYears();int jour=diff.getDays();
            if(annee<=0&&mois>0) return String.valueOf(mois)+" mois";
            else if(mois<=0&&jour>0) return String.valueOf(jour)+" jour";
            else return String.valueOf(annee)+" ans";



        }




        public static ArrayList<Uri> listePhotos(String id){
        ArrayList<Uri> photos = new ArrayList<>();
            StorageReference listRef = FirebaseStorage.getInstance().getReference().child(id+"/");
            listRef.listAll()
                    .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                        @Override
                        public void onSuccess(ListResult listResult) {
                            for (StorageReference item : listResult.getItems()) {
                                item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        photos.add(uri);
                                    }
                                });
                            }
                        }
                    });
            return photos;
        }




    }


