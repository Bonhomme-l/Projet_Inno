package com.example.petup.activities.ajouter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petup.Animal;
import com.example.petup.HomeActivity;
import com.example.petup.Profil;
import com.example.petup.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AjouterActivity2 extends AppCompatActivity {
    private String prenom;
    private String type;
    private String race;
    private String date;
    private boolean repro;
    private boolean sexe;
    private boolean sterelise;
    private String pelage;
    private String taille;
    private String yeux;
    private boolean exterieur;
    private String ville;

    private TextView ajouter;

    private EditText description;
    private String txtDescription;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    StorageReference storageRef;
    private StorageTask uploadTask;

    private RecyclerView liste_photos;
    private ListePhotosAdaptater adaptater;
    private static ImageView ajouter_photo;
    private static ImageView ajouter_autre_photo;
    private static ImageView photo_affiche;
    private String pp_url;
    private static boolean premier_photo=false;

    private ArrayList<Uri> photos;

    ProgressDialog pd;

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
        sexe = intent.getBooleanExtra("sexe",false);

        description = findViewById(R.id.description_ajouter);

        ajouter = findViewById(R.id.ajouter_ajouter);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference();

        pd = new ProgressDialog(this);

        ajouter_photo = findViewById(R.id.ajouter_photo_ajouter2);
        ajouter_autre_photo = findViewById(R.id.ajouter_autre_photo_ajouter2);
        photo_affiche = findViewById(R.id.afficher_photo_ajouter2);

        liste_photos = findViewById(R.id.recycler_liste_photos_ajouter);
        adaptater = new ListePhotosAdaptater();
        liste_photos.setHasFixedSize(true);
        liste_photos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        liste_photos.setAdapter(adaptater);

        ajouter_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               choisirphotos();
            }
        });

        ajouter_autre_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choisirphotos();
            }
        });

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtDescription = description.getText().toString();
                if (TextUtils.isEmpty(txtDescription)) {
                    Toast.makeText(AjouterActivity2.this, "Veuillez entrer une description ", Toast.LENGTH_SHORT).show();
                } else if(adaptater.getPhotos().isEmpty()) {
                    Toast.makeText(AjouterActivity2.this, "Veuillez ajouter au moins une photo", Toast.LENGTH_SHORT).show();

                }else{
                    ajouter();
                }

            }
        });
    }

    public void choisirphotos(){
        final CharSequence[] items={"Choisir dans la gallerie","Prendre une photo"};
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Photos").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which].equals("Prendre une photo")) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePictureIntent,1001);
                }else{
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent,1);
                }
            }
        }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.show();
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = null;
        if(requestCode==1&& resultCode== RESULT_OK&&data != null) {
            uri = data.getData();
        }
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            uri = getImageUri(this,imageBitmap);
        }
        if(!premier_photo){
            ajouter_photo.setVisibility(View.INVISIBLE);
            ajouter_autre_photo.setVisibility(View.VISIBLE);
        }
        else premier_photo=true;
        photo_affiche.setImageURI(uri);
        adaptater.addPhoto(uri);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public static void afficherPhoto(Uri uri){
        photo_affiche.setImageURI(uri);
    }

    public static void retourDebut(){
        Uri uri = null;
        premier_photo=false;
        photo_affiche.setImageURI(uri);
        ajouter_photo.setVisibility(View.VISIBLE);
        ajouter_autre_photo.setVisibility(View.INVISIBLE);
    }

    public void ajouter(){
            pd.setMessage("Un instant ... ");
            pd.show();

            photos=adaptater.getPhotos();
            pp_url=photos.get(0).toString();


            String id_profil = mAuth.getCurrentUser().getUid();
            db.collection("Profils").document(id_profil).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Profil profil = documentSnapshot.toObject(Profil.class);
                    ville = profil.getVille();



        Animal animal = new Animal(prenom,type,sexe,race,date,sterelise,txtDescription,pelage,yeux,taille,exterieur,repro,id_profil,pp_url,ville);

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

                            DocumentReference update = db.collection("animaux").document(id_animal);
                            update.update("id", id_animal);


                            for(Uri uri : photos){
                                StorageReference photo = storageRef.child(id_animal +"/"+ uri.getLastPathSegment());
                                uploadTask = photo.putFile(uri);
                                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    }
                                });
                            }

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
            });
        }

}