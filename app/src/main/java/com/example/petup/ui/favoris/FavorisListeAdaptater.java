package com.example.petup.ui.favoris;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petup.HomeActivity;
import com.example.petup.Profil;
import com.example.petup.R;
import com.example.petup.Animal;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavorisListeAdaptater extends RecyclerView.Adapter<FavorisListeAdaptater.RecycleViewHolder_Fav> {
    ArrayList<Animal> animaux = new ArrayList<>();
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public FavorisListeAdaptater(ArrayList<String> animaux_id) {

        for(String id : animaux_id){
            FirebaseFirestore.getInstance().collection("Animaux").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    animaux.add(documentSnapshot.toObject(Animal.class));
                }
            });
        }


    }

    public int getItemViewType(final int position) {
        return R.layout.item_fav;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public RecycleViewHolder_Fav onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecycleViewHolder_Fav(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder_Fav holder, int position) {
        holder.getPrenom().setText(animaux.get(position).getPrenom());
        holder.getRace().setText(animaux.get(position).getRace());

        Uri uri = Uri.parse(animaux.get(position).getId()) ;
        storageRef.child(animaux.get(position).getId()+"/"+ uri.getLastPathSegment()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.getPP());
            }
        });
    }

    @Override
    public int getItemCount() {
        return animaux.size();
    }


    public class RecycleViewHolder_Fav extends RecyclerView.ViewHolder {
        private TextView prenom;
        private TextView race;
        private TextView age;
        //private ImageView genre;
        private ImageView photo_profil;

        public RecycleViewHolder_Fav(@NonNull View v) {
            super(v);
            prenom = v.findViewById(R.id.prenom_fav);
            race = v.findViewById(R.id.race_fav);
            //genre = v.findViewById(R.id.genre_home);
            photo_profil = v.findViewById(R.id.photoProfil_fav);
        }
        public TextView getPrenom(){
            return prenom;
        }
        public TextView getRace(){
            return race;
        }
        public TextView getAge(){
            return age;
        }
       // public ImageView getGenre(){ return genre; }
        public ImageView getPP(){ return photo_profil; }
    }
}

