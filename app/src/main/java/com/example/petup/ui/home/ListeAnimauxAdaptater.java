package com.example.petup.ui.home;

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

import java.util.ArrayList;

public class ListeAnimauxAdaptater extends RecyclerView.Adapter<ListeAnimauxAdaptater.RecycleViewHolder_LA> {
    ArrayList<Animal> animaux;
    ArrayList<Animal> animaux_full;

    public ListeAnimauxAdaptater(ArrayList<Animal> animaux) {
        this.animaux=animaux;
        animaux_full= new ArrayList<>(animaux);
    }

    public int getItemViewType(final int position) {
        return R.layout.item_animaux_home;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public RecycleViewHolder_LA onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecycleViewHolder_LA(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder_LA holder, int position) {
        holder.getPrenom().setText(animaux.get(position).prenom);
        holder.getRace().setText(animaux.get(position).race);
        holder.getDistance().setText(String.valueOf(animaux.get(position).distance)+" km");
        holder.getPP().setImageResource(animaux.get(position).photo_profil);
        holder.getGenre().setImageResource(animaux.get(position).img_genre);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ProfilAnimalActivity.class); //On "prend" l'autre page
                String message = animaux.get(position).prenom;
                i.putExtra("Mess",message);//Avoir un message quand on change d'activité
                view.getContext().startActivity(i); //On la start
            }
        });
    }

    @Override
    public int getItemCount() {
        return animaux.size();
    }

    public Filter getFilter() {
        return exampleFilter;
    }
    public Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Animal> filteredList = new ArrayList<>();
            String filter = constraint.toString().toLowerCase();
            if (filter == null || filter.length() == 0) {
                filteredList.addAll(animaux_full);
            } else {
                for (Animal a : animaux) {
                    if ((a.type.toLowerCase()).contains(filter)) {
                        filteredList.add(a);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            animaux.clear();
            animaux.addAll((ArrayList<Animal>) results.values);
            notifyDataSetChanged();
        }
    };

    public class RecycleViewHolder_LA extends RecyclerView.ViewHolder {
        private TextView prenom;
        private TextView race;
        private TextView distance;
        private ImageView genre;
        private ImageView photo_profil;

        public RecycleViewHolder_LA(@NonNull View v) {
            super(v);
            prenom = v.findViewById(R.id.prenom_home);
            race = v.findViewById(R.id.race_home);
            distance = v.findViewById(R.id.distance_home);
            genre = v.findViewById(R.id.genre_home);
            photo_profil = v.findViewById(R.id.photoProfil_home);
        }
        public TextView getPrenom(){
            return prenom;
        }
        public TextView getRace(){
            return race;
        }
        public TextView getDistance(){
            return distance;
        }
        public ImageView getGenre(){ return genre; }
        public ImageView getPP(){ return photo_profil; }
    }
}

