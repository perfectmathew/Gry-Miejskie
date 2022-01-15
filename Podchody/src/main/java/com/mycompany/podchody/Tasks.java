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
public class Tasks {
    private int ID;
    private int IDExam;
    private String Tresc;
    private String Obraz;
    private String OdpA;
    private String OdpB;
    private String OdpC;
    private String OdpD;
    private String PoprawanaOdp;
    
    public Tasks(int _id, int _idexam, String _tresc,String _obraz ,String _odpa, String _odpb, String _odpc, String _odpd, String _poprawnaodp){
        ID = _id;
        IDExam = _idexam;
        Tresc = _tresc;
        Obraz = _obraz;
        OdpA = _odpa;
        OdpB = _odpb;
        OdpC = _odpc;
        OdpD = _odpd;
        PoprawanaOdp = _poprawnaodp;
    }
      public Tasks(int _id,String _tresc,String _obraz ,String _odpa, String _odpb, String _odpc, String _odpd, String _poprawnaodp){
        ID = _id;
        Tresc = _tresc;
        Obraz = _obraz;
        OdpA = _odpa;
        OdpB = _odpb;
        OdpC = _odpc;
        OdpD = _odpd;
        PoprawanaOdp = _poprawnaodp;
    }
    public Tasks(int _id, int _idexam, String _tresc){
        ID = _id;
        IDExam = _idexam;
        Tresc = _tresc;
    }
    public String getObraz(){
        return Obraz;
    }
    public int getID(){
        return  ID;
    }
    public int getIDExam(){
        return IDExam;
    }
    public String getTresc(){
        return Tresc;
    }
    public String getOdpA(){
        return OdpA;
    }
    public String getOdpB(){
        return OdpB;
    }
    public String getOdpC(){
        return OdpC;
    }
     public String getOdpD(){
        return OdpD;
    }
    public String getPoprawanaOdp(){
        return PoprawanaOdp;
    }
}
