package com.example.andrea.reserva_tu_diversion.Adaptador;

public class Tematicaa {
    private String Cod_Tematica, Descripcion;
    public  Tematicaa(String Cod_Tematica, String Descripcion){
        this.Cod_Tematica=Cod_Tematica;
        this.Descripcion=Descripcion;
    }
    public Tematicaa()
    {

    }
    public String getCod_Tematica()
    {
        return Cod_Tematica;
    }
    public String setCod_Tematica()
    {
        return Cod_Tematica;
    }
    public String getDescripcion()
    {
        return Descripcion;
    }
    public String setDescripcion()
    {
        return Descripcion;
    }
    public String toString(){return Cod_Tematica + "\n" + Descripcion;}
}
