package com.uy.cra.petagram.fragment;

import com.uy.cra.petagram.adapter.MascotaAdaptador;
import com.uy.cra.petagram.pojo.Mascota;
import com.uy.cra.petagram.adapter.MascotaAdaptador;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);


}
