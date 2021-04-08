package com.example.petup.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petup.R;

import java.util.ArrayList;

public class ListeAnimauxAdaptater extends RecyclerView.Adapter<RecycleViewHolder_LA> {
    ArrayList<Animal> animaux;

    public ListeAnimauxAdaptater(ArrayList<Animal> animaux)
    {
        this.animaux=animaux;
    }

    public int getItemViewType(final int position) {
        return R.layout.item_animaux_home;
    }

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
        holder.getSexe().setImageResource(animaux.get(position).img_sexe);
    }

    @Override
    public int getItemCount() {
        return animaux.size();
    }
}
class RecycleViewHolder_LA extends RecyclerView.ViewHolder {

    private TextView prenom;
    private TextView race;
    private TextView distance;
    private ImageView sexe;
    public RecycleViewHolder_LA(@NonNull View v) {
        super(v);
        prenom = v.findViewById(R.id.prenom_home);
        race = v.findViewById(R.id.race_home);
        distance = v.findViewById(R.id.distance_home);
        sexe = v.findViewById(R.id.sexe_home);

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
    public ImageView getSexe(){
        return sexe;
    }




}
