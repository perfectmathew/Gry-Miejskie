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
import java.util.List;
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
public class GamesServlet extends HttpServlet {
   String connectionURL = "jdbc:mysql://localhost:3308/podchody?autoReconnect=true&useSSL=false";
   Connection con = null;
   Statement ps = null;
   ResultSet rs = null;   
    public String SQL = "SELECT * FROM Games ORDER BY ID DESC";
    ArrayList<Games> games = new ArrayList<Games>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(!games.isEmpty()){
            games.clear();
        }
        response.setContentType("text/html;charset=UTF-8");
            try {    
               Class.forName("com.mysql.cj.jdbc.Driver");
           } catch (ClassNotFoundException ex) {
           }
            try {
               con = DriverManager.getConnection(connectionURL, "root", "root");
           } catch (SQLException ex) {
           }
        
       try {
           ps = con.createStatement();
           rs = ps.executeQuery(SQL);
           while(rs.next()){
               games.add(new Games(rs.getInt("ID"),rs.getString("Kod"),rs.getString("Nazwa"),rs.getString("obraz")));
           }
       } catch (SQLException ex) {
           Logger.getLogger(GamesServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
      request.setAttribute("listOfGames",games);
      request.getRequestDispatcher("/games.jsp").forward(request, response);   
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
