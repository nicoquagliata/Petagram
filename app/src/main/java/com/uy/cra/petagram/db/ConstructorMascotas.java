package com.uy.cra.petagram.db;

import android.content.ContentValues;
import android.content.Context;

import com.uy.cra.petagram.R;
import com.uy.cra.petagram.pojo.Mascota;

import java.util.ArrayList;

public class ConstructorMascotas {

    private static final int LIKE = 1;
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos(){

        BaseDatos db = new BaseDatos(context);
        //insertarMascotas(db);
        return db.obtenerTodasLasMascotas();

    }
    public ArrayList<Mascota> obtenerDatosFavoritos(){

        BaseDatos db = new BaseDatos(context);
        insertarMascotas(db);
        return db.obtenerMascotasFavoritas();

    }

    public void insertarMascotas(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE, "Fatiga");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.d1);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE, "Chloe");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.d2);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE, "China");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.d3);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE, "Pepe");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.d4);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE, "Che");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.d5);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE, "Socorro");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.d6);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE, "Amigacho");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.d7);

        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE, "Pompita");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.d8);

        db.insertarMascota(contentValues);
    }

    public void darLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstanteBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES, LIKE);

        db.insertarLikeMascota(contentValues);

    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }
}
