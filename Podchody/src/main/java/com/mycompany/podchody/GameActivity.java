/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.podchody;

/**
 *
 * @author Perfectamthew
 */
public class GameActivity {
    private int ID;
    private  String Imie;
    private  String Nazwisko;
    private int IDGracza;
    private  int IDGRY;
    private  String DataRozpoczecia;
    private  int IDTestu;
    private  String NazwaTestu;
    private String pozX;
    private String pozY;
    public GameActivity(int _id, String _imie, String _nazwisko, int _idGracza, int _IDGRY, String _data, int _idtestu, String _NazwaTestu, String _pozx, String _pozy){
        ID = _id;
        Imie = _imie;
        Nazwisko = _nazwisko;
        IDGracza = _idGracza;
        IDGRY = _IDGRY;
        DataRozpoczecia = _data;
        IDTestu = _idtestu;
        NazwaTestu = _NazwaTestu;
        pozX = _pozx;
        pozY = _pozy;
    }
    public int getID(){
        return ID;
    }
    public int getIDGracza(){
        return IDGracza;
    }
    public int getIDGRY(){
        return IDGRY;
    }
    public int getIDTestu(){
        return IDTestu;
    }
    public String getImie(){
        return Imie;
    }
    public String getNazwisko(){
        return Nazwisko;
    }
    public String getData(){
        return DataRozpoczecia;
    }
    public String getNazwa(){
        return NazwaTestu;
    }
    public String getPozX(){
        return pozX;
    }
    public String getPozY(){
        return pozY;
    }
}
