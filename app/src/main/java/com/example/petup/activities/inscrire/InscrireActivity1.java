package com.example.petup.activities.inscrire;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.petup.R;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class InscrireActivity1 extends AppCompatActivity  {
    private EditText nom;
    private EditText prenom;
    private TextView date;
    private EditText adresse;
    private EditText ville;
    private RadioButton femme;
    private RadioButton homme;
    private RadioButton autre;

    private CardView pp_charger;

    private ImageView pp;
    private String pp_url="1";

    private TextView suivant;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrire1);

        nom = findViewById(R.id.nom_incrire);
        prenom = findViewById(R.id.prenom_inscrire);
        date = findViewById(R.id.ddn_inscrire);
        adresse = findViewById(R.id.adresse_inscrire);
        ville = findViewById(R.id.ville_inscrire);

        femme=findViewById(R.id.femme_radio_incrire);
        homme=findViewById(R.id.homme_radio_inscrire);
        autre=findViewById(R.id.autre_radio_inscrire);
        autre.setChecked(true);

        pp=findViewById(R.id.pp_inscription);
        pp_charger = findViewById(R.id.choisirpp_inscription);

        suivant = findViewById(R.id.bouton_suivant_inscription1);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendrier();
            }
        });

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtNom = nom.getText().toString();
                String txtPrenom = prenom.getText().toString();
                String txtDate = date.getText().toString();
                String txtAdresse = adresse.getText().toString();
                String txtVille = ville.getText().toString();

                if (TextUtils.isEmpty(txtNom) || TextUtils.isEmpty(txtPrenom)||TextUtils.isEmpty(txtDate)
                        || TextUtils.isEmpty(txtAdresse) || TextUtils.isEmpty(txtVille)){
                    Toast.makeText(InscrireActivity1.this, "Veuillez l'ensemble des champs les champs ", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(InscrireActivity1.this, InscrireActivity2.class);
                    intent.putExtra("nom",txtNom);
                    intent.putExtra("prenom",txtPrenom);
                    intent.putExtra("date",txtDate);
                    intent.putExtra("adresse",txtAdresse);
                    intent.putExtra("ville",txtVille);
                    intent.putExtra("photo_profil_url",pp_url);
                    String txtSexe="";
                    if(femme.isChecked()) txtSexe="Femme";
                    if(homme.isChecked()) txtSexe="Homme";
                    if(autre.isChecked()) txtSexe="Autre";
                    intent.putExtra("sexe",txtSexe);
                    startActivity(intent);
                }
            }
        });

        pp_charger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choisirpp();
            }
        });

    }

    public void choisirpp(){
        final CharSequence[] items={"Choisir dans la gallerie","Prendre une photo"};
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("Photo de profil").setItems(items, new DialogInterface.OnClickListener() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri;
        if(requestCode==1&& resultCode== RESULT_OK&&data != null) {
            uri = data.getData();
            pp_url=uri.toString();
            pp.setImageURI(uri);
        }
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            uri = getImageUri(this,imageBitmap);
            pp_url=uri.toString();
            pp.setImageURI(uri);
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public void calendrier(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                int a=Calendar.getInstance().get(Calendar.YEAR);
                int m=Calendar.getInstance().get(Calendar.MONTH);
                int j=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

                int age= (a-year)+(m-monthOfYear)/12+(j-dayOfMonth)/365;

                if(age<18||age<=0){
                    Toast.makeText(InscrireActivity1.this,"Vous devez avoir au moins 18 ans pour vous inscrire !",Toast.LENGTH_SHORT).show();
                    date.setText("");
                }
                else{
                    DateFormat formater = new SimpleDateFormat("d MMMM yyyy",new Locale("FR","fr"));
                    Calendar cal = new GregorianCalendar(year,monthOfYear,dayOfMonth-1);
                    cal.add(Calendar.DATE, 1);
                    Date dat = cal.getTime();
                    String d = formater.format(dat);
                    date.setText(d);
                }
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListener,
                2000,
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();


    }





}


