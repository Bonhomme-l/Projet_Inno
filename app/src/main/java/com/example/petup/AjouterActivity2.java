package com.example.petup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petup.ui.favoris.FavorisFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AjouterActivity2 extends AppCompatActivity {
    private String prenom;
    private String type;
    private String race;
    private String date;
    private boolean repro;
    private String sexe;
    private boolean sterelise;
    private String pelage;
    private String taille;
    private String yeux;
    private boolean exterieur;

    private TextView ajouter;

    private EditText description;
    private String txtDescription;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter2);

        Intent intent = getIntent();
        prenom = intent.getStringExtra("prenom");
        type = intent.getStringExtra("type");
        race = intent.getStringExtra("race");
        date = intent.getStringExtra("date");
        repro = intent.getBooleanExtra("reproduction", false);
        sterelise = intent.getBooleanExtra("sterelisé", false);
        pelage = intent.getStringExtra("pelage");
        taille = intent.getStringExtra("taille");
        yeux = intent.getStringExtra("yeux");
        exterieur = intent.getBooleanExtra("exterieur", false);
        sexe = intent.getStringExtra("sexe");

        description = findViewById(R.id.description_ajouter);

        ajouter = findViewById(R.id.ajouter_ajouter);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        pd = new ProgressDialog(this);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtDescription = description.getText().toString();
                if (TextUtils.isEmpty(txtDescription)) {
                    Toast.makeText(AjouterActivity2.this, "Veuillez entrer une description ", Toast.LENGTH_SHORT).show();
                } else {
                    ajouter();
                }
            }
        });
    }

        public void ajouter(){
            pd.setMessage("Un instant ... ");
            pd.show();

            String id_profil = mAuth.getCurrentUser().getUid();

            Map<String, Object> animal = new HashMap<>();
            animal.put("Prenom", prenom);
            animal.put("Date de naissance",date);
            animal.put("Description", txtDescription);
            animal.put("Exterieur", exterieur);
            animal.put("Genre", sexe);
            animal.put("Id_Profil",id_profil);
            animal.put("Pelage", pelage);
            animal.put("Race", race);
            animal.put("Reproduction", repro);
            animal.put("Sterelisation", sterelise);
            animal.put("Taille", taille);
            animal.put("Type", type);
            animal.put("Yeux", yeux);

            db.collection("Animaux")
                    .add(animal)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            pd.dismiss();
                            Toast.makeText(AjouterActivity2.this, "Animal ajouté !", Toast.LENGTH_SHORT).show();
                            String id_animal = documentReference.getId();
                            DocumentReference washingtonRef = db.collection("Profils").document(id_profil);
                            washingtonRef.update("Animaux", FieldValue.arrayUnion(id_animal));
                            Intent intent = new Intent(AjouterActivity2.this, HomeActivity.class);
                            intent.putExtra("Eleveur",true);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(AjouterActivity2.this, "Erreur :"+e, Toast.LENGTH_SHORT).show();
                        }
                    });
        }

}