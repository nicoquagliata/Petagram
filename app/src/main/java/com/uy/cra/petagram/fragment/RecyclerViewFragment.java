package com.uy.cra.petagram.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uy.cra.petagram.pojo.Mascota;
import com.uy.cra.petagram.adapter.MascotaAdaptador;
import com.uy.cra.petagram.R;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {

    private static ArrayList<Mascota> mascotas = new ArrayList<Mascota>();;
    private RecyclerView listaMascotas;
    public MascotaAdaptador adaptador;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);


        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);



        // Grid Layout
//        GridLayoutManager glm = new GridLayoutManager(getActivity(), 1);
//        listaContactos.setLayoutManager(glm);

        inicializarListaMascotas();
        inicializarAdaptador();

        return v;
    }

    private void inicializarAdaptador(){
        adaptador = new MascotaAdaptador(mascotas, getActivity());
        listaMascotas.setAdapter(adaptador);
    }

    public void inicializarListaMascotas(){
        mascotas.add(new Mascota("Fatiga",      1, R.drawable.d1));
        mascotas.add(new Mascota("Chloe",       3, R.drawable.d2));
        mascotas.add(new Mascota("Botija",      5, R.drawable.d3));
        mascotas.add(new Mascota("Sultán",      4, R.drawable.d4));
        mascotas.add(new Mascota("Pepe",        6, R.drawable.d5));
        mascotas.add(new Mascota("Piolina",     8, R.drawable.d6));
        mascotas.add(new Mascota("Silvestre",   1, R.drawable.d7));
        mascotas.add(new Mascota("Rocky",       1, R.drawable.d8));
    }

    public static final ArrayList recuperarListaMascotas(){
        ArrayList<Mascota> masc = new ArrayList<Mascota>();
        masc = mascotas;

        return masc;
    }

}
