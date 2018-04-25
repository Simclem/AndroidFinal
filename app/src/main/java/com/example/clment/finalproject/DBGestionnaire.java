package com.example.clment.finalproject;

/**
 * Created by Clément on 02/04/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBGestionnaire {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "concert1.db";

    private static final String TABLE = "concert";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_CONCERT = "NomConcert";
    private static final int NUM_COL_ISBN = 1;
    private static final String COL_GROUPE = "NomGroupe";
    private static final int NUM_COL_TITRE = 2;
    private SQLiteDatabase bdd;
    private Database maBaseSQLite;


    public DBGestionnaire(Context context){
        //On crée la BDD et sa table
        this.maBaseSQLite = new Database(context, NOM_BDD, null, VERSION_BDD);
    }
    public void open(Context context){
        //on ouvre la BDD en écriture
        //Database maBaseSQLite = new Database(context, NOM_BDD, null, VERSION_BDD);

        bdd = maBaseSQLite.getWritableDatabase();
    }
    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }
    public SQLiteDatabase getBDD(){
        return bdd;
    }
    public ArrayList<Concert> getAllConcert(){
    //public void getConcert(){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)

        //bdd.execSQL("SELECT * FROM concert");

        Cursor c = bdd.query(Database.TABLE, new String[] {Database.COL_ID, Database.COL_CONCERT, Database.COL_GROUPE}, null, null, null, null, null);

        if (c.getCount() == 0)
            return null;


        ArrayList<Concert> toReturn = new ArrayList<Concert>();
        c.moveToFirst();

        for(int i= 0 ; i < c.getCount(); i ++){
            Concert concert = new Concert();
            concert.setNomConcert(c.getString(NUM_COL_ISBN));
            concert.setNomGroupe(c.getString(NUM_COL_TITRE));
            c.moveToNext();
            toReturn.add(concert);
        }
        return toReturn;
    }


    private Concert cursorToConcert(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Concert concert = new Concert();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor

        concert.setNomConcert(c.getString(NUM_COL_ISBN));
        concert.setNomGroupe(c.getString(NUM_COL_TITRE));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return concert;
    }


    public long insertLivre(Concert concert){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(Database.COL_CONCERT, concert.getNomConcert());
        values.put(Database.COL_GROUPE, concert.getNomGroupe());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(Database.TABLE, null, values);
    }

}
