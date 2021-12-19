/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.podchody;

public class Games {
    private int ID;
    private String KOD;
    private String Nazwa;
    private String Obraz;
    public Games(int _id, String _kod, String _nazwa, String _obraz){
        ID = _id;
        KOD = _kod;
        Nazwa = _nazwa;
        Obraz = _obraz;
    }
    public int getID(){
        return ID;
    }
    public String getKOD(){
        return KOD;
    }
    public String getNazwa(){
        return Nazwa;
    }
    public String getObraz(){
        return Obraz;
    }
}
