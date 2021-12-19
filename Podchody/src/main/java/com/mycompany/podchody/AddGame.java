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
public class AddGame extends HttpServlet {
   String connectionURL = "jdbc:mysql://localhost:3308/podchody?autoReconnect=true&useSSL=false";
   Connection con = null;
   PreparedStatement st = null;
   ResultSet rs = null;
   String SQL = "INSERT INTO Games (Nazwa, Kod, Obraz, Aktywna) VALUES (?,?,?,?)";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String Obraz = "";
            response.setContentType("text/html;charset=UTF-8");
           try {    
               Class.forName("com.mysql.cj.jdbc.Driver");
           } catch (ClassNotFoundException ex) {
           }
           try {
               con = DriverManager.getConnection(connectionURL, "root", "root");
           } catch (SQLException ex) {
           }
        String Nazwa = request.getParameter("nazwa");
        String Kod = request.getParameter("kod");
        if(request.getParameter("obraz")!=null){
            Obraz = request.getParameter("obraz");
        }else{
            Obraz = "https://cdn.pixabay.com/photo/2021/09/07/07/11/game-console-6603120_960_720.jpg";
        }
        if(Obraz.equals("")){
                 Obraz = "https://cdn.pixabay.com/photo/2021/09/07/07/11/game-console-6603120_960_720.jpg";
        }
       try {
           st = con.prepareStatement(SQL);
           st.setString(1, Nazwa);
           st.setString(2, Kod);
           st.setString(3, Obraz);
           st.setBoolean(4, true);
           st.execute();
       } catch (SQLException ex) {
           Logger.getLogger(AddGame.class.getName()).log(Level.SEVERE, null, ex);
       }
   
          request.getRequestDispatcher("games").forward(request, response);         
    
  
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
