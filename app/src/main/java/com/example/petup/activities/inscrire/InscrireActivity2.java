package com.example.petup.activities.inscrire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petup.Profil;
import com.example.petup.activities.ajouter.AjouterActivity;
import com.example.petup.HomeActivity;
import com.example.petup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InscrireActivity2 extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private RadioButton radioEleveur;

    private EditText email;
    private EditText mdp;
    private EditText mdp_confirme;
    private TextView inscrire;

    private String nom;
    private String prenom;
    private String date;
    private String adresse;
    private String ville;
    private String sexe;
    private String pp_url;

    StorageReference storageRef;
    private StorageTask uploadTask;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrire2);

        email = findViewById(R.id.email_inscrire);
        mdp = findViewById(R.id.mdp_inscrire);
        mdp_confirme = findViewById(R.id.mdp_confirme_inscrire);
        inscrire = findViewById(R.id.inscrire_login);

        Intent i = getIntent();
        nom=i.getStringExtra("nom");
        prenom=i.getStringExtra("prenom");
        date=i.getStringExtra("date");
        adresse=i.getStringExtra("adresse");
        ville=i.getStringExtra("ville");
        sexe=i.getStringExtra("sexe");
        pp_url=i.getStringExtra("photo_profil_url");

        storageRef = FirebaseStorage.getInstance().getReference();

        radioEleveur=findViewById(R.id.eleveur_radio_incrire);
        radioEleveur.setChecked(true);

        inscrire = findViewById(R.id.inscrire_inscrire);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        pd = new ProgressDialog(this);
        inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtEmail = email.getText().toString();
                String txtMdp = mdp.getText().toString();
                String txtMdp_c = mdp_confirme.getText().toString();

                if (TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtMdp)||TextUtils.isEmpty(txtMdp_c)){
                    Toast.makeText(InscrireActivity2.this, "Veuillez l'ensemble des champs les champs ", Toast.LENGTH_SHORT).show();
                }
                else if(!txtMdp.equals(txtMdp_c)){
                    Toast.makeText(InscrireActivity2.this, "Veuillez entrer le même mot de passe ", Toast.LENGTH_SHORT).show();
                }
                else if (txtMdp.length()<6){
                    Toast.makeText(InscrireActivity2.this, "Veuillez entrer un mot de passe robuste ", Toast.LENGTH_SHORT).show();
                }
                else {
                    inscription(txtEmail,txtMdp);
                }
            }
        });
    }


    private void inscription( String email, String mdp) {
        pd.setMessage("Un instant ... ");
        pd.show();

        mAuth.createUserWithEmailAndPassword(email,mdp).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                boolean eleveur;
                if(radioEleveur.isChecked()) eleveur=true;
                else eleveur=false;
                ArrayList<String> animaux = new  ArrayList<>();
                Profil profil=new Profil(adresse,animaux,date,eleveur,nom,pp_url,prenom,sexe,ville);

                String id = mAuth.getCurrentUser().getUid();

                db.collection("Profils").document(id).set(profil).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            pd.dismiss();
                            Toast.makeText(InscrireActivity2.this, "Compte Crée !", Toast.LENGTH_SHORT).show();
                             if(!pp_url.equals("")){
                            Uri pp_uri = Uri.parse(pp_url);
                            StorageReference photo = storageRef.child(id +"/"+ pp_uri.getLastPathSegment());
                            uploadTask = photo.putFile(pp_uri);
                            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                }
                            });
                             }

                            if(eleveur){
                                AlertDialog.Builder builder = new AlertDialog.Builder(InscrireActivity2.this);
                                builder.setMessage("Bienvenu.e sur notre application en tant qu'adopteur ! Voulez-vous ajouter une boule de poils à adopter ?")
                                        .setIcon(R.drawable.paw_orange)
                                        .setTitle("Nouvel Utilisateur !")
                                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(InscrireActivity2.this , AjouterActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("Plus tard", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(InscrireActivity2.this , HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.putExtra("Eleveur",true);
                                        startActivity(intent);
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(InscrireActivity2.this);
                                builder.setMessage("Bienvenu.e ! Vous pouver voir les animaux à adopter proches de chez vous dès maintenant")
                                        .setIcon(R.drawable.paw_orange)
                                        .setTitle("Nouvel Utilisateur !")
                                        .setNegativeButton("D'accord !", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(InscrireActivity2.this , HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.putExtra("Eleveur",false);
                                        startActivity(intent);
                                    }
                                });
                                AlertDialog dialog2 = builder.create();
                                dialog2.show();
                            }

                        }
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(InscrireActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}