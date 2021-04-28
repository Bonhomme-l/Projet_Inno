package com.example.petup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfilAnimalActivity  extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilanimal);
        Intent i = getIntent();
        String message = i.getStringExtra("Mess");//récuperer le message
        ((TextView)findViewById(R.id.prenom_profilanimal)).setText(message);

    }


}
