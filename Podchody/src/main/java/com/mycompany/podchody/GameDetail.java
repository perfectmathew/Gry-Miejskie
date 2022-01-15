/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.podchody;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perfectamthew
 */
public class GameDetail extends HttpServlet {
String connectionURL = "jdbc:mysql://localhost:3308/podchody?autoReconnect=true&useSSL=false";
public String GAMEID;
Connection con = null;
boolean found = false;
PreparedStatement st = null;
String GAMENAME;
ResultSet rs = null;     
String SQLCheck = "SELECT * FROM Games WHERE ID = ?";
    ArrayList<Players> infograczy = new ArrayList<>();
    ArrayList<GameActivity> informacjeogrze = new ArrayList<>();
    ArrayList<Exams> infotesty = new ArrayList<>();
    ArrayList<Exams> wszystkietesty = new ArrayList<>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.removeAttribute("gameActivty");
        request.removeAttribute("gameExams");
        request.removeAttribute("gameUsers");
        if(!infograczy.isEmpty()){
            infograczy.clear();
        }else if(!infotesty.isEmpty()){
            infotesty.clear();
        }else if(!wszystkietesty.isEmpty()){
            wszystkietesty.clear();
        }else if(!informacjeogrze.isEmpty()){
           informacjeogrze.clear();
           
        }
        found =false;
        response.setContentType("text/html;charset=UTF-8");
          GAMEID =  request.getParameter("id");
            try {    
               Class.forName("com.mysql.cj.jdbc.Driver");
           } catch (ClassNotFoundException ex) {
           }
           try {
               con = DriverManager.getConnection(connectionURL, "root", "root");
           } catch (SQLException ex) {  
           }
          String SQLex = "SELECT * FROM exams";
        try{
         Statement stat = con.createStatement();
         ResultSet gof = stat.executeQuery(SQLex); 
         while(gof.next()){
             wszystkietesty.add(new Exams(gof.getInt("ID"),gof.getString("Nazwa")));
         }
         stat.close();
           }catch (SQLException ex) {
            Logger.getLogger(GameDetail.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
            st = con.prepareStatement(SQLCheck);
            st.setInt(1,Integer.parseInt(GAMEID));
            rs = st.executeQuery();
            
            if(rs.next()){
                GAMENAME= rs.getString("Nazwa");
                found=true;
            }
             } catch (SQLException ex) {
            Logger.getLogger(GameDetail.class.getName()).log(Level.SEVERE, null, ex);
            }
              
            if(found==true){
                request.setAttribute("ALLEXAMS", wszystkietesty);
                request.setAttribute("GAMENAME", GAMENAME);
                request.setAttribute("GAMEID", GAMEID);
          if(request.getParameter("action")!=null){
          if(request.getParameter("action").equals("users")){
             try {
                  String usersql = "SELECT * FROM players INNER JOIN Games ON (players.IDGAME = Games.ID) WHERE Games.ID = ?";
                  PreparedStatement infusers = con.prepareStatement(usersql);
                  infusers.setInt(1,Integer.parseInt(GAMEID));
                  ResultSet rsusers = infusers.executeQuery();
                  while(rsusers.next()){
                      infograczy.add(new Players(rsusers.getInt("players.ID"),rsusers.getInt("Games.ID"),rsusers.getString("players.KodDstepu"),rsusers.getString("players.Imie"),rsusers.getString("players.Nazwisko")));
                  }
              } catch (SQLException ex) {
                  Logger.getLogger(GameDetail.class.getName()).log(Level.SEVERE, null, ex);
              }
             
          request.setAttribute("gameUsers",infograczy);    
          request.getRequestDispatcher("/gamelogic.jsp").forward(request, response);   
          }else if(request.getParameter("action").equals("exams")){
              try {
                  String SQLexams = "SELECT * from exams INNER JOIN Games ON (exams.IDGame = Games.ID) WHERE Games.ID = ?";
                  PreparedStatement infexams = con.prepareStatement(SQLexams);
                  infexams.setInt(1,Integer.parseInt(GAMEID));
                  ResultSet rsexams = infexams.executeQuery();
                  while(rsexams.next()){
                      infotesty.add(new Exams(rsexams.getInt("Exams.ID"),rsexams.getInt("Games.ID"),rsexams.getString("Exams.Nazwa"),rsexams.getString("Exams.WysGeograficzna"),rsexams.getString("Exams.SzerGeograficzna"),rsexams.getFloat("Exams.ProgZaliczenia")));
                  }
              } catch (SQLException ex) {
                  Logger.getLogger(GameDetail.class.getName()).log(Level.SEVERE, null, ex);
              }
                request.setAttribute("gameExams",infotesty);
                request.getRequestDispatcher("/gamelogic.jsp").forward(request, response);
          }else if(request.getParameter("action").equals("activity")){
              String someSQl = "SELECT * FROM GameActivity INNER JOIN Games ON (GameActivity.Gra = Games.ID) INNER JOIN players ON (GameActivity.Gracz = players.ID) INNER JOIN exams ON (GameActivity.Test = exams.ID) INNER JOIN Position ON (GameActivity.Gra = Position.Gra) WHERE GameActivity.Gra = ?";
              if(!informacjeogrze.isEmpty()){
           informacjeogrze.clear();
        }
              try {
                  PreparedStatement some = con.prepareStatement(someSQl);
                  some.setInt(1, Integer.parseInt(GAMEID));
                  ResultSet someactivity = some.executeQuery();
                  while(someactivity.next()){
                      informacjeogrze.add(
                              new GameActivity(someactivity.getInt("GameActivity.ID"),someactivity.getString("players.KodDstepu"),
                              someactivity.getString("players.KodDstepu"),someactivity.getInt("GameActivity.Gracz"),someactivity.getInt("GameActivity.Gra"),
                              someactivity.getString("GameActivity.CzasRozpoczecia"), someactivity.getInt("GameActivity.Test"),
                              someactivity.getString("exams.Nazwa"),
                                 someactivity.getString("Position.PozX"), someactivity.getString("Position.PozY"))
                      );
                  }
              } catch (SQLException ex) {
                  Logger.getLogger(GameDetail.class.getName()).log(Level.SEVERE, null, ex);
              }
              
           request.setAttribute("gameActivty",informacjeogrze);    
          request.getRequestDispatcher("/gamelogic.jsp").forward(request, response);  
          }else{
           request.getRequestDispatcher("/gamelogic.jsp").forward(request, response); 
          }
          }else{
           request.getRequestDispatcher("/gamelogic.jsp").forward(request, response);   
          } 
            }else{
               request.getRequestDispatcher("games").forward(request, response);  
            }
  
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
