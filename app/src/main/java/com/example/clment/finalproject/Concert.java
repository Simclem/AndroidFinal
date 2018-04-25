package com.example.clment.finalproject;

/**
 * Created by Clément on 02/04/2018.
 */

public class Concert {
    private String NomConcert;
    private String NomGroupe;

    public Concert(String nomConcert, String nomGroupe) {
        NomConcert = nomConcert;
        NomGroupe = nomGroupe;
    }
    public Concert() {

    }
    public String getNomConcert(){
        return NomConcert;
    }
    public String getNomGroupe(){
        return NomGroupe;
    }
    public void setNomConcert(String newName){
        NomConcert = newName;
    }
    public void setNomGroupe(String newName){
        NomGroupe = newName;
    }
    public String  toString(){ return "Nom Concert : " + NomConcert + "   NomGroupe : "+ NomGroupe ;}
}
