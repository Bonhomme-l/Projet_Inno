package com.example.petup.ui.home;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petup.R;

public class TypeAnimauxFiltreAdaptater extends RecyclerView.Adapter<TypeAnimauxFiltreAdaptater.RecycleViewHolder_TAF> {
    String types[]= {"Chien", "Chat", "Lapin", "Souris", "Rat","Furet"};
    boolean clicks[] = {false, false, false, false, false,false};
    int icone = R.drawable.ic_dashboard_black_24dp;
    int clické = R.drawable.ic_home_black_24dp;
    int nbr_clicks = 0;
    ListeAnimauxAdaptater adaptater;

    public TypeAnimauxFiltreAdaptater(ListeAnimauxAdaptater adaptater) {
        this.adaptater=adaptater;
    }

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
        String type_a = types[position];
        holder.getType().setText(type_a);
        holder.getIconeType().setImageResource(icone);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<5;i++){
                    if(clicks[i]==true) nbr_clicks++;
                }
                Log.d("nbr clicks", String.valueOf(nbr_clicks));
                if(!clicks[position]){
                    if(nbr_clicks>=1){
                    }else{
                        adaptater.getFilter().filter(type_a);
                        clicks[position]=true;
                        holder.getIconeType().setImageResource(clické);
                        nbr_clicks=0;
                    }
                }else{
                    adaptater.getFilter().filter("");
                    for(int i=0;i<5;i++){
                        clicks[i]=false;
                    }
                    holder.getIconeType().setImageResource(icone);
                    nbr_clicks=0;
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return 6;
    }



    public class RecycleViewHolder_TAF extends RecyclerView.ViewHolder {

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
}


