package com.example.petup.ui.favoris;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petup.R;
import com.example.petup.ui.home.HomeFragment;

public class FavorisFragment extends Fragment {

    RecyclerView recycler;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favoris, container, false);

        recycler = root.findViewById(R.id.recyclerview_fav);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
        FavorisListeAdaptater adaptater =new FavorisListeAdaptater(HomeFragment.animaux);
        recycler.setAdapter(adaptater);

        return root;
    }


}