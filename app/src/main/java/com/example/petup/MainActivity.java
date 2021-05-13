package com.example.petup;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petup.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity  {
    private EditText email;
    private EditText password;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.mdp_login);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

    }


    public void connexion(View v){
                    String txt_email = email.getText().toString();
                    String txt_password = password.getText().toString();
                    if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                        Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    } else {
                        connecter(txt_email , txt_password);
                    }
    }

    private void connecter(String email, String password) {
        mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    db.collection("Profils")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    Intent intent = new Intent(MainActivity.this , HomeActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            if(document.getId().equals(mAuth.getCurrentUser().getUid())){
                                                if((boolean)document.getData().get("Eleveur")==true){
                                                    intent.putExtra("Eleveur",true);
                                                }else{
                                                    intent.putExtra("Eleveur",false);
                                                }
                                            }
                                            startActivity(intent);
                                            finish();
                                        }
                                    } else {
                                        Log.w("Erreur", "Erreur", task.getException());
                                    }
                                }
                            });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void inscrire(View v){
        Intent intent = new Intent(this, InscrireActivity1.class);
        startActivity(intent);
    }


}