package com.example.petup.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.petup.R;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class HomeFragment extends Fragment  {

    private RecyclerView recycler_v,recycler_h;
    public static ArrayList<Animal> animaux = dataAnimaux();

    public SearchView searchView;
    public ListeAnimauxAdaptater adaptater_la=new ListeAnimauxAdaptater(animaux);
    public TypeAnimauxFiltreAdaptater adaptater_ta=new TypeAnimauxFiltreAdaptater(adaptater_la);


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recycler_v = root.findViewById(R.id.recycler_home_vertical);
        recycler_v.setHasFixedSize(true);
        recycler_v.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recycler_v.setAdapter(adaptater_la);

        recycler_h = root.findViewById(R.id.recycler_home_horizontal);
        recycler_h.setHasFixedSize(true);
        recycler_h.setLayoutManager(new LinearLayoutManager(root.getContext(),LinearLayoutManager.HORIZONTAL,false));
        recycler_h.setAdapter(adaptater_ta);

        searchView= (SearchView) root.findViewById(R.id.rechercher_menu);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                adaptater_la.getFilter().filter(query);
                return false;
            }
        });

        return root;
    }

    public static ArrayList<Animal> dataAnimaux(){
        ArrayList<Animal> animaux = new ArrayList<>();
        ArrayList<Integer> phot = new ArrayList<>();
        animaux.add(new Animal("Flipper","Chien",false,
                "Chihuahua",new GregorianCalendar(2010,2,26),false,
                "Chien Mignon",20,"1,allée Franck","Les Mureaux",R.drawable.ic_dashboard_black_24dp,phot));
        return animaux;
    }
}