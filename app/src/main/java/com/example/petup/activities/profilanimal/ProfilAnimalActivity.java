package com.example.petup.activities.profilanimal;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.example.petup.HomeActivity;
import com.example.petup.Profil;
import com.example.petup.R;
import com.example.petup.Animal;
import com.example.petup.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ProfilAnimalActivity  extends AppCompatActivity {
    ViewPager mViewPager;
    RecyclerView recycler;

    TextView nom, ville, adresse, description,age;

    String id;

    ArrayList<Uri> photos = new ArrayList<>();

    ViewPagerAdapter mViewPagerAdapter;

    Animal animal;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilanimal);

        db = FirebaseFirestore.getInstance();

        nom = findViewById(R.id.prenom_profilanimal);
        ville = findViewById(R.id.ville_profilanimal);
        age = findViewById(R.id.age_profilanimal);
        description = findViewById(R.id.description_profilanimal);

        Intent i = getIntent();

        id=i.getStringExtra("Id");

        age.setText(i.getStringExtra("Age"));
        nom.setText(i.getStringExtra("Nom"));
        ville.setText(i.getStringExtra("Ville"));
        adresse.setText(i.getStringExtra("Adresse"));
        description.setText(i.getStringExtra("Description"));


        mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);
        mViewPagerAdapter = new ViewPagerAdapter(ProfilAnimalActivity.this, HomeActivity.listePhotos(id));
        mViewPager.setAdapter(mViewPagerAdapter);


        FirebaseFirestore.getInstance().collection("Animaux").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                animal = documentSnapshot.toObject(Animal.class);
            }
        });

        recycler = findViewById(R.id.recycle_cate_profilanimal);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recycler.setAdapter(new CategoriesAnimauxAdaptater(animal));
    }


}
