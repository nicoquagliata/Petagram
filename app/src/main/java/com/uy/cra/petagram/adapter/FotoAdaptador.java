package com.uy.cra.petagram.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uy.cra.petagram.R;
import com.uy.cra.petagram.pojo.Foto;

import java.util.ArrayList;

public class FotoAdaptador extends RecyclerView.Adapter<FotoAdaptador.FotoViewHolder> {

    ArrayList<Foto> fotos;
    Activity activity;

    public FotoAdaptador(ArrayList<Foto> fotos, Activity activity){
        this.fotos = fotos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public FotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_foto, parent, false);
        return new FotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final FotoAdaptador.FotoViewHolder holder, int position) {
        final Foto foto = fotos.get(position);
        Integer fotosLikes = foto.getLikes();
        holder.imgFoto.setImageResource(foto.getFoto());
        holder.tvLikesCV.setText(fotosLikes.toString());

        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer fotoLikes = foto.getLikes();
                fotoLikes +=1;
                foto.setLikes(fotoLikes);

                holder.tvLikesCV.setText(fotoLikes.toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    public static class FotoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvLikesCV;
        private ImageButton btnLike;

        public FotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvLikesCV = (TextView) itemView.findViewById(R.id.tvLikesCV);
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);

        }
    }
}
