package com.uy.cra.petagram;

public class Mascota {
    private String nombre;
    private int likes;
    private int foto;

    public Mascota(String nombre, int likes, int foto) {
        this.nombre = nombre;
        this.likes = likes;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
