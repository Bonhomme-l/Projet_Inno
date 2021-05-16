package com.example.petup.activities.ajouter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petup.R;
import java.util.ArrayList;

public class ListePhotosAdaptater extends RecyclerView.Adapter<ListePhotosAdaptater.RecycleViewHolder_LP>{
    public ArrayList<Uri> photos = new ArrayList<>();

    public int getItemViewType(final int position) {
        return R.layout.item_photo_list;
    }

    public RecycleViewHolder_LP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ListePhotosAdaptater.RecycleViewHolder_LP(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder_LP holder, int position) {
        holder.getPhoto().setImageURI(photos.get(position));

        holder.getPhoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AjouterActivity2.afficherPhoto(photos.get(position));
            }
        });

        holder.getSupprimer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removePhoto(photos.get(position));
                if(photos.size()==0){
                    AjouterActivity2.retourDebut();
                }else{
                    AjouterActivity2.afficherPhoto(photos.get(0));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void addPhoto(Uri photo){
        photos.add(photo);
        notifyDataSetChanged();
    }

    public void removePhoto(Uri photo){
        photos.remove(photo);
        notifyDataSetChanged();
    }

    public ArrayList<Uri> getPhotos(){
        return photos;
    }

    public class RecycleViewHolder_LP extends RecyclerView.ViewHolder {
    private ImageView photo;
    private ImageView supprimer;

    public RecycleViewHolder_LP(@NonNull View v) {
        super(v);
        photo = v.findViewById(R.id.photo_list);
        supprimer = v.findViewById(R.id.supprimer_photo);
    }

    public ImageView getPhoto(){
        return photo;
    }
    public ImageView getSupprimer(){
            return supprimer;
        }
}
}
