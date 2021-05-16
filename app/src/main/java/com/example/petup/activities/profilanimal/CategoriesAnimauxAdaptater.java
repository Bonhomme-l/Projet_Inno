package com.example.petup.activities.profilanimal;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petup.HomeActivity;
import com.example.petup.R;
import com.example.petup.Animal;

public class CategoriesAnimauxAdaptater extends RecyclerView.Adapter<CategoriesAnimauxAdaptater.RecycleViewHolder_CA> {
    String cat[]={"Genre","Race","Age","Type","Stérélisé","Pelage","Yeux","Taille","Extérieur"};
    Animal animal;
    String cont[];

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CategoriesAnimauxAdaptater(Animal animal){
        this.animal=animal;

        boolean g=animal.isGenre();
        String genre;
        if(g) genre="Femelle";
        else genre="Mâle";

        boolean s=animal.isSterelisation();
        String sterelise;
        if(s) sterelise="Oui";
        else sterelise="Non";

        boolean e=animal.isExterieur();
        String ext;
        if(e) ext="Oui";
        else ext="Non";

        cont= new String[]{genre, animal.getRace(), HomeActivity.calculAge(animal.getNaissance()),animal.getType(),sterelise,animal.getPelage(),animal.getYeux(),animal.getTaille()+" cm",ext};
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


