package com.uy.cra.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<Mascota> mascotas = new ArrayList<Mascota>();;
    private RecyclerView listaMascotas;
    public MascotaAdaptador adaptador;

//    SwipeRefreshLayout sfiMiIndicador;
//    ListView idMiLista;
//    Adapter adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.miActionBar);

        setSupportActionBar(myToolbar);

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);

        inicializarListaMascotas();
        inicializarAdaptador();



//
//        agregarFAB();
//
//        sfiMiIndicador = (SwipeRefreshLayout) findViewById(R.id.sfiMiIndicador);
//        idMiLista = (ListView) findViewById(R.id.idMiLista);
//
//        String[] planetas = getResources().getStringArray(R.array.planetas);
//
//        idMiLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, planetas));
//
//        sfiMiIndicador.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//refrescandoContenido();
//            }
//        });
    }

//    public void refrescandoContenido(){
//        String[] planetas = getResources().getStringArray(R.array.planetas);
//        idMiLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, planetas));
//        sfiMiIndicador.setRefreshing(false);
//    }

//    public void agregarFAB(){
//        FloatingActionButton miFAB = (FloatingActionButton) findViewById(R.id.fabMiFAB);
//        miFAB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Toast.makeText(getBaseContext(), getResources().getString(R.string.mensaje), Toast.LENGTH_LONG).show();
//                Snackbar.make(view, getResources().getString(R.string.mensaje), Snackbar.LENGTH_LONG)
//                        .setAction(getString(R.string.texto_accion), new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Log.i("SNACKBAR", "click en snackbar");
//                            }
//                        })
//                        .setActionTextColor(getResources().getColor(R.color.colorPrimaryDark))
//                        .show();
//            }
//        });
//    }

    private void inicializarAdaptador(){
        adaptador = new MascotaAdaptador(mascotas, this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.fav:
            Toast.makeText(getBaseContext(), "fav clicked", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, Favoritos.class);
            startActivity(intent);
            return(true);
        case R.id.exit:
            finish();
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

}
