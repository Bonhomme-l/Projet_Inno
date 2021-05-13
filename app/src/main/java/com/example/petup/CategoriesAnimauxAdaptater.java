package com.example.petup;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petup.HomeActivity;
import com.example.petup.R;
import com.example.petup.ui.home.Animal;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CategoriesAnimauxAdaptater extends RecyclerView.Adapter<CategoriesAnimauxAdaptater.RecycleViewHolder_CA> {
    String cat[]={"Genre","Race","Age","Stérélisé","Pelage","Yeux","Taille","Extérieur"};
    Animal animal;
    String cont[];

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CategoriesAnimauxAdaptater(Animal animal){
        this.animal=animal;

        boolean g=animal.genre;
        String genre;
        if(g) genre="Femelle";
        else genre="Mâle";

        boolean s=animal.sterelisation;
        String sterelise;
        if(s) sterelise="Oui";
        else sterelise="Non";

        boolean e=animal.exterieur;
        String ext;
        if(e) ext="Oui";
        else ext="Non";

        cont= new String[]{genre, animal.race, HomeActivity.calculAge(animal.naissance),sterelise,animal.pelage,animal.yeux,String.valueOf(animal.taille)+" cm",ext};
    }


    public int getItemViewType(final int position) {
        return R.layout.item_cate_profilanimal;
    }

    public RecycleViewHolder_CA onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecycleViewHolder_CA(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder_CA holder, int position) {
        holder.getCategorie().setText(cat[position]);
        holder.getContenu().setText(cont[position]);


    }


    @Override
    public int getItemCount() {
        return cat.length;
    }

    public class RecycleViewHolder_CA extends RecyclerView.ViewHolder {
        private TextView categorie;
        private TextView contenu;

        public RecycleViewHolder_CA(@NonNull View v) {
            super(v);
            categorie = v.findViewById(R.id.categorie);
            contenu = v.findViewById(R.id.contenu);
        }

        public TextView getCategorie(){
            return categorie;
        }
        public TextView getContenu(){
            return contenu;
        }
    }
}


