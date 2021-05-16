package com.example.petup.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petup.Animal;
import com.example.petup.HomeActivity;
import com.example.petup.MainActivity;
import com.example.petup.activities.profilanimal.ProfilAnimalActivity;
import com.example.petup.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListeAnimauxAdaptater extends RecyclerView.Adapter<ListeAnimauxAdaptater.RecycleViewHolder_LA> {
    ArrayList<Animal> animaux;
    ArrayList<Animal> animaux_full;
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    public ListeAnimauxAdaptater(ArrayList<Animal> animaux) {
        this.animaux = animaux;
        animaux_full = new ArrayList<>(animaux);
    }

    public int getItemViewType(final int position) {
        return R.layout.item_animaux_home;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public RecycleViewHolder_LA onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecycleViewHolder_LA(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder_LA holder, int position) {
        holder.getPrenom().setText(animaux.get(position).getPrenom());
        holder.getRace().setText(animaux.get(position).getRace());
        holder.getVille().setText(animaux.get(position).getVille());
        holder.getAge().setText(HomeActivity.calculAge(animaux.get(position).getNaissance()));
        holder.getDistance().setText("40 km");
        Uri uri = Uri.parse(animaux.get(position).getId()) ;
        storageRef.child(animaux.get(position).getId()+"/"+ uri.getLastPathSegment()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.getPP());
            }
        });

        if(animaux.get(position).isGenre())holder.getGenre().setImageResource(R.drawable.female);
        else holder.getGenre().setImageResource(R.drawable.male);

        switch (animaux.get(position).type) {
            case "Chien":
                holder.getTypeImg().setImageResource(R.drawable.chienbleu);
                break;
            case "Chat":
                holder.getTypeImg().setImageResource(R.drawable.chatbleu);
                break;
            case "Lapin":
                holder.getTypeImg().setImageResource(R.drawable.lapinbleu);
                break;
            case "Souris":
                holder.getTypeImg().setImageResource(R.drawable.sourisbleu);
                break;
            case "Rat":
                holder.getTypeImg().setImageResource(R.drawable.ratbleu);
                break;
            case "Furet":
                holder.getTypeImg().setImageResource(R.drawable.furetbleu);
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ProfilAnimalActivity.class);
                i.putExtra("Id", animaux.get(position).getId());
                i.putExtra("Age",HomeActivity.calculAge(animaux.get(position).getNaissance()));
                i.putExtra("Photo Profil", animaux.get(position).getPp_url());
                i.putExtra("Nom", animaux.get(position).getPrenom());
                i.putExtra("Ville", animaux.get(position).getVille());
                i.putExtra("Description", animaux.get(position).getDescription());

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

    class RecycleViewHolder_LA extends RecyclerView.ViewHolder {
        private TextView prenom;
        private TextView race;
        private TextView distance;
        private TextView ville;
        private TextView age;
        private ImageView genre;
        private ImageView typeimg;
        private ImageView photo_profil;

        public RecycleViewHolder_LA(@NonNull View v) {
            super(v);
            typeimg= v.findViewById(R.id.typeimg_home);
            age= v.findViewById(R.id.age_home);
            ville = v.findViewById(R.id.ville_home);
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
        public TextView getVille(){ return ville; }
        public TextView getAge(){ return age; }
        public ImageView getTypeImg(){ return typeimg; }
    }
}







