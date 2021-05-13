package com.example.petup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AjouterActivity extends AppCompatActivity {
    private EditText prenom;
    private Spinner type;
    private String txtType;
    private EditText race;
    private TextView date;
    private Spinner repro;
    private String txtRepro;
    private RadioButton femelle;
    private Spinner sterelise;
    private String txtSter;
    private EditText pelage;
    private EditText taille;
    private EditText yeux;
    private Spinner exterieur;
    private String txtExt;
    private TextView suivant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        prenom = findViewById(R.id.prenom_ajouter);
        type = findViewById(R.id.spinner_type_ajouter);
        race = findViewById(R.id.race_ajouter);
        repro = findViewById(R.id.spinner_reproduction_ajouter);
        date = findViewById(R.id.ddn_ajouter);
        femelle = findViewById(R.id.femelle_radio_ajouter);
        femelle.setChecked(true);
        sterelise = findViewById(R.id.spinner_sterelise_ajouter);
        pelage = findViewById(R.id.pelage_ajouter);
        taille = findViewById(R.id.taille_ajouter);
        yeux = findViewById(R.id.yeux_ajouter);
        exterieur = findViewById(R.id.spinner_ext_ajouter);
        suivant = findViewById(R.id.suivant_ajouter);


        type.setAdapter(adapterSpinner(R.array.liste_type));
        type.setOnItemSelectedListener(new TypeSpinnerClass());

        sterelise.setAdapter(adapterSpinner(R.array.liste_ouinon));
        sterelise.setOnItemSelectedListener(new SterSpinnerClass());

        repro.setAdapter(adapterSpinner(R.array.liste_ouinon));
        repro.setOnItemSelectedListener(new ReproSpinnerClass());

        exterieur.setAdapter(adapterSpinner(R.array.liste_blancouinon));
        exterieur.setOnItemSelectedListener(new ExtSpinnerClass());



        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendrier();
            }
        });

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtPrenom = prenom.getText().toString();
                String txtRace = race.getText().toString();
                String txtDate = date.getText().toString();
                String txtPelage = pelage.getText().toString();
                String txtTaille = taille.getText().toString();
                String txtYeux = yeux.getText().toString();

                if (TextUtils.isEmpty(txtPrenom) || TextUtils.isEmpty(txtRace)||TextUtils.isEmpty(txtType)||TextUtils.isEmpty(txtDate)||TextUtils.isEmpty(txtRepro)){
                    Toast.makeText(AjouterActivity.this, "Veuillez l'ensemble des champs les champs ", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean repro,ext,ster;
                    Intent intent = new Intent(AjouterActivity.this, AjouterActivity2.class);
                    intent.putExtra("prenom",txtPrenom);
                    intent.putExtra("type",txtType);
                    intent.putExtra("race",txtRace);
                    intent.putExtra("date",txtDate);
                    if(txtRepro.equals("Oui")) repro=true;
                    else repro= false;
                    intent.putExtra("reproduction",repro);
                    if(txtSter.equals("Oui")) ster=true;
                    else ster= false;
                    intent.putExtra("sterelisé",ster);
                    intent.putExtra("pelage",txtPelage);
                    intent.putExtra("taille",txtTaille);
                    intent.putExtra("yeux",txtYeux);
                    if(txtExt.equals("Oui")) ext=true;
                    else ext= false;
                    intent.putExtra("exterieur",ext);
                    String txtSexe="";
                    if(femelle.isChecked()) txtSexe="Femelle";
                    else txtSexe="Mâle";
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
                    String d = dayOfMonth+"/"+monthOfYear+"/"+year;
                    date.setText(d);
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

    public ArrayAdapter<CharSequence> adapterSpinner(int liste){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                liste, R.layout.spinner_text);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        return adapter;
    }

    public class TypeSpinnerClass implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
            txtType = parent.getItemAtPosition(position).toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }

    public class ExtSpinnerClass implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
            txtExt = parent.getItemAtPosition(position).toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }
    public class SterSpinnerClass implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
            txtSter = parent.getItemAtPosition(position).toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }

    public class ReproSpinnerClass implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
            txtRepro = parent.getItemAtPosition(position).toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }
}