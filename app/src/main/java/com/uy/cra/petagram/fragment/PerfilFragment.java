package com.uy.cra.petagram.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uy.cra.petagram.R;
import com.uy.cra.petagram.adapter.FotoAdaptador;
import com.uy.cra.petagram.pojo.Foto;

import java.util.ArrayList;

public class PerfilFragment extends Fragment {

    private static ArrayList<Foto> fotos = new ArrayList<Foto>();;
    private RecyclerView listaFotos;
    public FotoAdaptador adaptador;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        listaFotos = (RecyclerView) v.findViewById(R.id.rvFotos);

        // Grid Layout
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        listaFotos.setLayoutManager(glm);

        inicializarListaFotos();
        inicializarAdaptador();

        return v;
    }

    private void inicializarAdaptador(){
        adaptador = new FotoAdaptador(fotos, getActivity());
        listaFotos.setAdapter(adaptador);
    }

    public void inicializarListaFotos(){
        fotos.add(new Foto(5, R.drawable.d2));
        fotos.add(new Foto(3, R.drawable.d1));
        fotos.add(new Foto(5, R.drawable.d2));
        fotos.add(new Foto(4, R.drawable.d1));
        fotos.add(new Foto(6, R.drawable.d3));
        fotos.add(new Foto(8, R.drawable.d1));
    }
}