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
public class EmxamsServlet extends HttpServlet {
   String connectionURL = "jdbc:mysql://localhost:3308/podchody?autoReconnect=true&useSSL=false";
   Connection con = null;
   ArrayList<Exams> exams = new ArrayList<>();
   int tasksquan;
   PreparedStatement ps = null;
   ResultSet rsps = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            response.setContentType("text/html;charset=UTF-8");
            tasksquan = 0;
            if(!exams.isEmpty()){
                exams.clear();
            }
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
            }
            try {
                con = DriverManager.getConnection(connectionURL, "root", "root");
            } catch (SQLException ex) {
                
            }
            String examsSQL = "SELECT * FROM exams";
        try { 
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(examsSQL);
            while(rs.next()){
                exams.add(new Exams(rs.getInt("ID"),Integer.parseInt(getNum(rs.getString("ID"))),rs.getString("Nazwa"),rs.getFloat("ProgZaliczenia")));
            }
        } catch (SQLException ex) {
           Logger.getLogger(EmxamsServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
        request.setAttribute("listexams",exams);
        request.getRequestDispatcher("/tasks.jsp").forward(request, response);   
    }
      public String getNum(String id){
        String ilosc = "";
           try {    
               Class.forName("com.mysql.cj.jdbc.Driver");
           } catch (ClassNotFoundException ex) {
             
           }
       try {
           con = DriverManager.getConnection(connectionURL, "root", "root");
       } catch (SQLException ex) {
       }
            String nmcm= "SELECT COUNT(ID) AS ilosc FROM tasks WHERE IDTestu = ?";
        try {
            ps =  con.prepareStatement(nmcm);
            ps.setInt(1,Integer.parseInt(id));
        } catch (SQLException ex) {
          
        }
        try {
            rsps = ps.executeQuery();
             if(rsps.next()){
                    if(rsps.getString("ilosc").equals('0')){
                          ilosc = "0";
                    }else {
                       ilosc = rsps.getString("ilosc"); 
                    }
             }
        } catch (SQLException ex) {
          
        }
        return ilosc;
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
