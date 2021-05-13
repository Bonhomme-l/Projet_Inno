package com.example.petup.ui.home;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petup.R;

public class TypeAnimauxFiltreAdaptater extends RecyclerView.Adapter<TypeAnimauxFiltreAdaptater.RecycleViewHolder_TAF> {
    String types[]= {"Chien", "Chat", "Lapin", "Souris", "Rat","Furet"};
    boolean clicks[] = {false, false, false, false, false,false};
    int icone[]={R.drawable.chienjaune, R.drawable.chatjaune, R.drawable.lapinjaune, R.drawable.sourisjaune, R.drawable.ratjaune, R.drawable.furetjaune};
    int violet = R.color.violet;
    int blanc = R.color.white;
    int clické[]={R.drawable.chienblanc, R.drawable.chatblanc, R.drawable.lapinblanc, R.drawable.sourisblanc, R.drawable.ratblanc, R.drawable.furetblanc};
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
        holder.getIconeType().setImageResource(icone[position]);


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
                        holder.getIconeType().setImageResource(clické[position]);
                        holder.getFond().setBackgroundColor(blanc);

                        nbr_clicks=0;
                        return;
                    }
                }else{
                    adaptater.getFilter().filter("");
                    for(int i=0;i<5;i++){
                        clicks[i]=false;
                    }
                    holder.getIconeType().setImageResource(icone[position]);
                    holder.getFond().setBackgroundColor(violet);
                    nbr_clicks=0;
                    return;
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
        private TextView fond;
        public RecycleViewHolder_TAF(@NonNull View v) {
            super(v);
            fond= v.findViewById(R.id.background_type);
            type = v.findViewById(R.id.type_filtre);
            icone = v.findViewById(R.id.image_type_filtre);

        }

        public TextView getType(){
            return type;
        }
        public ImageView getIconeType(){
            return icone;
        }
        public TextView getFond(){ return fond; }
    }

}


