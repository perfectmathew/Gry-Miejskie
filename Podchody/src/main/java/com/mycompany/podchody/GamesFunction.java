/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.podchody;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class GamesFunction extends HttpServlet {
String connectionURL = "jdbc:mysql://localhost:3308/podchody?autoReconnect=true&useSSL=false";
Connection con = null;
int GAMEID;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        GAMEID = Integer.parseInt(request.getParameter("gameid"));
          try {    
               Class.forName("com.mysql.cj.jdbc.Driver");
           } catch (ClassNotFoundException ex) {
           }
           try {
               con = DriverManager.getConnection(connectionURL, "root", "root");
           } catch (SQLException ex) {  
           }
        if(request.getParameter("type")!=null){
            switch (request.getParameter("type")) {
                case "adduser":
                    String imie = request.getParameter("imie");
                    String nazwisko = request.getParameter("nazwisko");
                    String kod = request.getParameter("code");
                    String SQL = "INSERT INTO players (Imie,Nazwisko,KodDstepu,IDGame) VALUES (?,?,?,?)";
                    try {
                        PreparedStatement st = con.prepareStatement(SQL);
                        st.setString(1, imie);
                        st.setString(2, nazwisko);
                        st.setString(3, kod);
                        st.setInt(4, GAMEID);
                        st.execute();
                        request.getRequestDispatcher("g?id="+GAMEID+"&action=users").forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(GamesFunction.class.getName()).log(Level.SEVERE, null, ex);
                    }   break;
                case "edituser":
                    break;
                case "deleteuser":
                    break;
                case "addexam":
                    String szerokosc = request.getParameter("szerokosc");
                    String wysokosc = request.getParameter("wysokosc");
                    int idtestu = Integer.parseInt(request.getParameter("idtestu"));
                    String examSQL = "UPDATE exams SET IDGame = ?, SzerGeograficzna = ?, WysGeograficzna = ? WHERE ID = ?";
                {
                    try {
                        PreparedStatement xc = con.prepareStatement(examSQL);
                        xc.setInt(1, GAMEID);
                        xc.setString(2, szerokosc);
                        xc.setString(3, wysokosc);
                        xc.setInt(4, idtestu);
                        xc.executeUpdate();
                       request.getRequestDispatcher("g?id="+GAMEID+"&action=exams").forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(GamesFunction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "deleteexam":
                    break;
                case "editexam":
                    break;
                default:
                    break;
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
