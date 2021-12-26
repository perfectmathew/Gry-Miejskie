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
public class ExamFunction extends HttpServlet {
   String connectionURL = "jdbc:mysql://localhost:3308/podchody?autoReconnect=true&useSSL=false";
   Connection con = null;
   int EXAMID;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String SQLp = "INSERT INTO tasks (Tresc,Obraz,OdpA,OdpB,OdpC,OdpD,PoprawnaOdp,IDTestu) VALUES (?,?,?,?,?,?,?,?)";
        String SQL = "INSERT INTO exams (Nazwa, ProgZaliczenia) VALUES (?,?)";
        String SQLd = "DELETE FROM tasks WHERE ID = ?";
        if(request.getParameter("examid")!=null)
        {
           EXAMID = Integer.parseInt(request.getParameter("examid"));
        }

               try {    
               Class.forName("com.mysql.cj.jdbc.Driver");
           } catch (ClassNotFoundException ex) {
           }
           try {
               con = DriverManager.getConnection(connectionURL, "root", "root");
           } catch (SQLException ex) {  
           }
     if(request.getParameter("type")!=null){
         switch(request.getParameter("type"))
         {
             case "addexam":
                 String nazwa = request.getParameter("name");
                 Float progzaliczenia = Float.parseFloat(request.getParameter("prog"));
                 {
                     
             try {
                 PreparedStatement st = con.prepareStatement(SQL);
                 st.setString(1, nazwa);
                 st.setFloat(2, progzaliczenia);
                 st.execute();
             } catch (SQLException ex) {
                 Logger.getLogger(ExamFunction.class.getName()).log(Level.SEVERE, null, ex);
             }   
                 }
             request.getRequestDispatcher("exams").forward(request, response);   
                     break;
             case "addquestion":
                 String tresc = request.getParameter("tresc");
                 String OdpA = request.getParameter("OdpA");
                 String OdpB = request.getParameter("OdpB");
                 String OdpC = request.getParameter("OdpC");
                 String OdpD = request.getParameter("OdpD"); 
                 String PoprawnaOdp = request.getParameter("PoprawnaOdp");
                 String obraz = "";
                 if(request.getParameter("obraz")!=null){
                     obraz = request.getParameter("obraz");
                 }else{
                     obraz ="";
                 }
                 {
             try {
                 PreparedStatement stu = con.prepareStatement(SQLp);
                 stu.setString(1, tresc);
                 stu.setString(2, obraz);
                 stu.setString(3, OdpA);
                 stu.setString(4, OdpB);
                 stu.setString(5, OdpC);
                 stu.setString(6, OdpD);
                 stu.setString(7, PoprawnaOdp);
                 stu.setInt(8,EXAMID);
                 stu.execute();
             } catch (SQLException ex) {
                 Logger.getLogger(ExamFunction.class.getName()).log(Level.SEVERE, null, ex);
             }
                 }
         request.getRequestDispatcher("e?id="+EXAMID).forward(request, response);   
                 break;
             case "editquestion":
                 break;
             case "deletequestion":
             {
                 String idtask = request.getParameter("taskid");
             try {
                 PreparedStatement std = con.prepareStatement(SQLd);
                 std.setInt(1, Integer.parseInt(idtask));
                 std.executeUpdate();
             request.getRequestDispatcher("e?id="+EXAMID).forward(request, response);   
             } catch (SQLException ex) {
                 Logger.getLogger(ExamFunction.class.getName()).log(Level.SEVERE, null, ex);
             }
                 
             }
                 break;
             default:
                 break;
         }
         
         
     }else{
         
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
