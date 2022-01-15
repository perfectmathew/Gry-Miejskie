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
public class TaskServlet extends HttpServlet {
   String connectionURL = "jdbc:mysql://localhost:3308/podchody?autoReconnect=true&useSSL=false";
   Connection con = null;
   ArrayList<Tasks> tasks = new ArrayList<>();
   ArrayList<Tasks> alltasks = new ArrayList<>();
String EXAMID;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if(!tasks.isEmpty()){
            tasks.clear();
        }
        if(!alltasks.isEmpty()){
            alltasks.clear();
            request.removeAttribute("alltasklist");
        }
        EXAMID = request.getParameter("id");
        request.setAttribute("IDOFEXAM", EXAMID);
        String SQL = "SELECT * FROM tasks WHERE IDTestu = ?";
        try {    
               Class.forName("com.mysql.cj.jdbc.Driver");
           } catch (ClassNotFoundException ex) {
           }
           try {
               con = DriverManager.getConnection(connectionURL, "root", "root");
           } catch (SQLException ex) {
           }     
       try {
           PreparedStatement st = con.prepareStatement(SQL);
           st.setInt(1, Integer.parseInt(EXAMID));
           ResultSet rs = st.executeQuery();
           while(rs.next()){
               tasks.add(new Tasks(rs.getInt("ID"),Integer.parseInt(EXAMID),rs.getString("Tresc"),rs.getString("Obraz"),rs.getString("OdpA"),rs.getString("OdpB"),rs.getString("OdpC"),rs.getString("OdpD"),rs.getString("PoprawnaOdp")));
           }
           Statement ts = con.createStatement();
           ResultSet sr = ts.executeQuery("SELECT * FROM tasks");
           while(sr.next()){
               alltasks.add(new Tasks(sr.getInt("ID"),Integer.parseInt(EXAMID),sr.getString("Tresc")));
           }
       } catch (SQLException ex) {
           Logger.getLogger(TaskServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
       request.setAttribute("alltasklist", alltasks);
       request.setAttribute("taskslist", tasks);
       request.getRequestDispatcher("/examlogic.jsp").forward(request, response); 
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
