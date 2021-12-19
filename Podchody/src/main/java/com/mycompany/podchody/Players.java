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
public class Players {
    private int ID;
    private int IDGame;
    private String KodDostepu;
    private String Imie;
    private String Nazwisko;
    public Players(int _id, int _idgame, String _koddostepu, String _imie, String _nazwisko){
        ID = _id;
        IDGame = _idgame;
        KodDostepu = _koddostepu;
        Imie = _imie;
        Nazwisko = _nazwisko;
    }
    public int getID(){
        return ID;
    }
    public int getGameID(){
        return IDGame;
    }
    public String getKod(){
        return KodDostepu;
    }
    public String getImie(){
        return Imie;
    }
    public String getNazwisko(){
        return Nazwisko;
    }
}
