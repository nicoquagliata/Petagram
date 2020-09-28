package com.uy.cra.petagram.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uy.cra.petagram.db.ConstructorMascotas;
import com.uy.cra.petagram.pojo.Mascota;
import com.uy.cra.petagram.R;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaAdaptador.MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvNombreCV.setText(mascota.getNombre());
        holder.tvLikesCV.setText(String.valueOf(mascota.getLikes()));

        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Integer mascotaLikes = mascota.getLikes();
//                mascotaLikes +=1;
//                mascota.setLikes(mascotaLikes);
                Toast.makeText(activity, "Diste like a " + mascota.getNombre(), Toast.LENGTH_SHORT).show();

                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascota);

                holder.tvLikesCV.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvLikesCV;
        private ImageButton btnLike;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreCV = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvLikesCV = (TextView) itemView.findViewById(R.id.tvLikesCV);
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);

        }
    }
}
