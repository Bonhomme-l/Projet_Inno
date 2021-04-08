package com.example.petup.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petup.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recycler_v,recycler_h;
    public ArrayList<Animal> animaux = dataAnimaux();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        // Add the following lines to create RecyclerView
        recycler_v = root.findViewById(R.id.recycler_home_vertical);
        recycler_v.setHasFixedSize(true);
        recycler_v.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recycler_v.setAdapter(new ListeAnimauxAdaptater(animaux));

        recycler_h = root.findViewById(R.id.recycler_home_horizontal);
        recycler_h.setHasFixedSize(true);
        recycler_h.setLayoutManager(new LinearLayoutManager(root.getContext(),LinearLayoutManager.HORIZONTAL,false));
        recycler_h.setAdapter(new TypeAnimauxFiltreAdaptater());

        return root;
    }

    /*
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rechercher_menu, menu);
    MenuItem searchItem = menu.findItem(R.id.recherche);
    SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }
        @Override
        public boolean onQueryTextChange(String newText) {
            adapter.getFilter().filter(newText);
            return false;
        }
    });
        return true;
}*/

    public ArrayList<Animal> dataAnimaux(){
        ArrayList<Animal> animaux = new ArrayList<>();
        ArrayList<Integer> phot = new ArrayList<>();
        Animal a = new Animal("Flipper","Chien",false,"Chihuahua","Chien mignon",20,R.drawable.ic_dashboard_black_24dp,phot);
        animaux.add(a);
        a = new Animal("Anabelle","Chat",false,"Tigre","Chat mignon",50,R.drawable.ic_dashboard_black_24dp,phot);
        animaux.add(a);
        a = new Animal("Ratatouille","Souris",true,"Souris","Chien mignon",80,R.drawable.ic_dashboard_black_24dp,phot);
        animaux.add(a);
        a = new Animal("Flipper","Chien",false,"Chihuahua","Chien mignon",20,R.drawable.ic_dashboard_black_24dp,phot);
        animaux.add(a);
        a = new Animal("Flipper","Chien",false,"Chihuahua","Chien mignon",20,R.drawable.ic_dashboard_black_24dp,phot);
        animaux.add(a);
        a = new Animal("Flipper","Chien",false,"Chihuahua","Chien mignon",20,R.drawable.ic_dashboard_black_24dp,phot);
        animaux.add(a);
        return animaux;

    }
}