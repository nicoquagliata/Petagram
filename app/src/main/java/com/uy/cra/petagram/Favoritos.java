package com.uy.cra.petagram;

import android.os.Bundle;

import com.uy.cra.petagram.adapter.MascotaAdaptador;
import com.uy.cra.petagram.fragment.RecyclerViewFragment;
import com.uy.cra.petagram.pojo.Mascota;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class Favoritos extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    public MascotaAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        listaMascotas = (RecyclerView) findViewById(R.id.rvFavoritos);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);

//      inicializarListaMascotas();
        mascotas = RecyclerViewFragment.recuperarListaMascotas();
        ArrayList<Mascota> mascotasFavoritas = new ArrayList<Mascota>();

        // ordenar por likes
            for (int i=0;i<mascotas.size();i++) {
                if(i>0){
                    if (mascotas.get(i).getLikes() > mascotasFavoritas.get(i-1).getLikes()){
                        mascotasFavoritas.add(0, mascotas.get(i));
                    } else {
                        mascotasFavoritas.add(mascotas.get(i));
                    }
                } else {
                    mascotasFavoritas.add(mascotas.get(i));
                }
            }
            mascotasFavoritas.subList(5, mascotasFavoritas.size()).clear();
            mascotas = mascotasFavoritas;

            inicializarAdaptador();


    }

    private void inicializarAdaptador(){
        adaptador = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.exit:
            finish();
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }
}