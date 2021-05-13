package com.example.petup.ui.favoris;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petup.ProfilAnimalActivity;
import com.example.petup.R;
import com.example.petup.ui.home.Animal;

import java.util.ArrayList;

public class FavorisListeAdaptater extends RecyclerView.Adapter<FavorisListeAdaptater.RecycleViewHolder_Fav> {
    ArrayList<Animal> animaux;

    public FavorisListeAdaptater(ArrayList<Animal> animaux) {
        this.animaux=animaux;
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
        holder.getPrenom().setText(animaux.get(position).prenom);
        holder.getRace().setText(animaux.get(position).race);
        holder.getPP().setImageResource(animaux.get(position).photo_profil);
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

