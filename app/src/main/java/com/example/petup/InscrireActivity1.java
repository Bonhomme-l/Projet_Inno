package com.example.petup;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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

    private TextView suivant;

    private DatePickerDialog.OnDateSetListener mDateSetListener;


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
                    String txtSexe="";
                    if(femme.isChecked()) txtSexe="F";
                    if(homme.isChecked()) txtSexe="H";
                    if(autre.isChecked()) txtSexe="A";
                    intent.putExtra("sexe",txtSexe);
                    startActivity(intent);
                }
            }
        });
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


