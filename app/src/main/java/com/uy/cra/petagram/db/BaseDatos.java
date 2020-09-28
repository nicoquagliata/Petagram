package com.uy.cra.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.uy.cra.petagram.pojo.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context, ConstanteBaseDatos.DATABASE_NAME, null, ConstanteBaseDatos.DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE "+ConstanteBaseDatos.TABLE_MASCOTA+"("+
                ConstanteBaseDatos.TABLE_MASCOTA_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE    + " TEXT, "+
                ConstanteBaseDatos.TABLE_MASCOTA_FOTO      + " INTEGER"+
                ")";

        String queryCrearTablaLikesMascota = "CREATE TABLE "+ConstanteBaseDatos.TABLE_LIKES_MASCOTA + "("          +
                ConstanteBaseDatos.TABLE_LIKES_MASCOTA_ID              + " INTEGER PRIMARY KEY AUTOINCREMENT, "     +
                ConstanteBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA     + " INTEGER, "                               +
                ConstanteBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES    + " INTEGER, "                               +
                "FOREIGN KEY (" + ConstanteBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + ") "                         +
                "REFERENCES " + ConstanteBaseDatos.TABLE_MASCOTA + "("+ ConstanteBaseDatos.TABLE_MASCOTA_ID +")"  +
                ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstanteBaseDatos.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXISTS " + ConstanteBaseDatos.TABLE_LIKES_MASCOTA);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas (){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstanteBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            String queryLikes = "SELECT COUNT("+ConstanteBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES+") as likes" +
                    " FROM " + ConstanteBaseDatos.TABLE_LIKES_MASCOTA +
                    " WHERE " + ConstanteBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + "=" + mascotaActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if(registrosLikes.moveToNext()){
                mascotaActual.setLikes(registrosLikes.getInt(0));
            } else {
                mascotaActual.setLikes(0);
            }

            mascotas.add(mascotaActual);
        }

        db.close();

        return mascotas;
    }

    public ArrayList<Mascota> obtenerMascotasFavoritas (){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        mascotas = obtenerTodasLasMascotas ();
        ArrayList<Mascota> mascotasFavoritas = new ArrayList<>();

        //recorro array mascotas
        for (int i=0;i<mascotas.size();i++) {

            int posicionAgregar=0;

            if(i>0){

                //recorro las mascotas ya agregadas
                for(int j=0; j<mascotasFavoritas.size(); j++){

                    // si la mascota nueva tiene menos likes que la posicion
                    if (mascotas.get(i).getLikes() < mascotasFavoritas.get(j).getLikes()){
                        posicionAgregar = j+1;

                    }
                }



                mascotasFavoritas.add(posicionAgregar, mascotas.get(i));

            } else {
                // agrego la primer mascota
                mascotasFavoritas.add(mascotas.get(i));
            }
        }


        mascotasFavoritas.subList(5, mascotasFavoritas.size()).clear();




        return mascotasFavoritas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstanteBaseDatos.TABLE_MASCOTA, null, contentValues);

        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstanteBaseDatos.TABLE_LIKES_MASCOTA, null, contentValues);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;

        String query = "SELECT COUNT ("+ConstanteBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES + ")" +
                " FROM " + ConstanteBaseDatos.TABLE_LIKES_MASCOTA +
                " WHERE " + ConstanteBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + "="+ mascota.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();

        return likes;
    }
}
