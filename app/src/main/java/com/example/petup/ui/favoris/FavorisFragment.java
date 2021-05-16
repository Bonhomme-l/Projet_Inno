package com.example.petup.ui.favoris;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petup.Animal;
import com.example.petup.HomeActivity;
import com.example.petup.Profil;
import com.example.petup.R;
import com.example.petup.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class FavorisFragment extends Fragment {

    RecyclerView recycler;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    public static ArrayList<String> animaux = new ArrayList<>();
    private String id;
    private Profil profil;
    FavorisListeAdaptater adaptater;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favoris, container, false);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Task<DocumentSnapshot> documentSnapshotTask = db.collection("Profils").document(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    profil = task.getResult().toObject(Profil.class);
                    ArrayList<String> a = new ArrayList<>(profil.getAnimaux());
                    HomeActivity.refresh2(a);

            }}

        });

        recycler = root.findViewById(R.id.recyclerview_fav);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(root.getContext(),2));
        Log.d("ehooo",animaux.get(0));
        if (!animaux.isEmpty()) {
            // Log.d("qssss",animaux.get(0));
            adaptater= new FavorisListeAdaptater(animaux);
            recycler.setAdapter(adaptater);
        }



        return root;
    }


    public void refresh(ArrayList<String> a){
        animaux.clear();animaux.addAll(a);
    }


}