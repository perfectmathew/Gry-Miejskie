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
public class Exams {
    private int ID;
    private int IDGame;
    private String Nazwa;
    private String WysGeograficzna;
    private String SzerGeograficzna;
    private float ProgZaliczenia;
    
    public Exams(int _id, int _idgame, String _nazwa, String _WysGeograficzna, String _SzerGeograficzna, float _ProgZaliczenia){
        ID = _id;
        IDGame = _idgame;
        Nazwa = _nazwa;
        WysGeograficzna = _WysGeograficzna;
        SzerGeograficzna = _SzerGeograficzna;
        ProgZaliczenia = _ProgZaliczenia;
    
    }
    public int getID(){
        return ID;
    }
    public int getGameID(){
        return IDGame;
    }
    public String getNazwa(){
        return Nazwa;
    }
    public String getWys(){
    return WysGeograficzna;
    }
    public String getSzer(){
        return SzerGeograficzna;
    }
    public float getProg(){
        return ProgZaliczenia;
    }
}
