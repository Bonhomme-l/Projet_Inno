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

public class TypeAnimauxFiltreAdaptater extends RecyclerView.Adapter<RecycleViewHolder_TAF> {
    String types[]= {"Chien", "Chat", "Lapin", "Souris", "Rat"};
    int icone = R.drawable.ic_dashboard_black_24dp;

    public int getItemViewType(final int position) {
        return R.layout.item_typeanimal_home;
    }

    public RecycleViewHolder_TAF onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecycleViewHolder_TAF(view);
    }


    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder_TAF holder, int position) {
        holder.getType().setText(types[position]);
        holder.getIconeType().setImageResource(icone);
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

class RecycleViewHolder_TAF extends RecyclerView.ViewHolder {

    private TextView type;
    private ImageView icone;
    public RecycleViewHolder_TAF(@NonNull View v) {
        super(v);
        type = v.findViewById(R.id.type_filtre);
        icone = v.findViewById(R.id.image_type_filtre);

    }

    public TextView getType(){
        return type;
    }
    public ImageView getIconeType(){
        return icone;
    }




}
