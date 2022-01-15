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


public class ExamFunction extends HttpServlet {
   String connectionURL = "jdbc:mysql://localhost:3308/podchody?autoReconnect=true&useSSL=false";
   Connection con = null;
   int EXAMID;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String SQLP = "INSERT INTO tasks (Tresc,Obraz,OdpA,OdpB,OdpC,OdpD,PoprawnaOdp) VALUES (?,?,?,?,?,?,?)";
        String SQLp = "INSERT INTO tasks (Tresc,Obraz,OdpA,OdpB,OdpC,OdpD,PoprawnaOdp,IDTestu) VALUES (?,?,?,?,?,?,?,?)";
        String SQL = "INSERT INTO exams (Nazwa, ProgZaliczenia, Podpowiedz) VALUES (?,?,?)";
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
                 String podpowiedz = request.getParameter("podpowiedz");
                 Float progzaliczenia = Float.parseFloat(request.getParameter("prog"));
                 {
                     
             try {
                 PreparedStatement st = con.prepareStatement(SQL);
                 st.setString(1, nazwa);
                 st.setFloat(2, progzaliczenia);
                 st.setString(3, podpowiedz);
                 st.execute();
             } catch (SQLException ex) {
                 Logger.getLogger(ExamFunction.class.getName()).log(Level.SEVERE, null, ex);
             }   
                 }
             request.getRequestDispatcher("exams").forward(request, response);   
                     break;
             case "addque":
             {
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
                     obraz ="NULL";
                 }
                  try {
                 PreparedStatement stu = con.prepareStatement(SQLP);
                 stu.setString(1, tresc);
                 stu.setString(2, obraz);
                 stu.setString(3, OdpA);
                 stu.setString(4, OdpB);
                 stu.setString(5, OdpC);
                 stu.setString(6, OdpD);
                 stu.setString(7, PoprawnaOdp);
                 stu.execute();
             } catch (SQLException ex) {
                 Logger.getLogger(ExamFunction.class.getName()).log(Level.SEVERE, null, ex);
             }
             }
                      request.getRequestDispatcher("questions").forward(request, response);   
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
                     obraz ="NULL";
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
             case "conquestion":
                 String pytanie = request.getParameter("idpytania");
                 {
                     PreparedStatement stw;
                 try {
                     String sdxwsd = "UPDATE tasks SET IDTestu = ? WHERE ID = ?";
                     stw = con .prepareStatement(sdxwsd);
                     stw.setInt(1, EXAMID);
                     stw.setInt(2,Integer.parseInt(pytanie));
                     stw.execute();
                 } catch (SQLException ex) {
                     Logger.getLogger(ExamFunction.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 }
                 request.getRequestDispatcher("e?id="+EXAMID).forward(request, response);   
                 break;

             case "editquestion":
                 String sqlu = "UPDATE tasks SET Tresc = ?, Obraz = ?, OdpA = ?, OdpB = ?, OdpC = ?, OdpD = ?, PoprawnaOdp = ? WHERE Tresc = ?";
                 String tresce = request.getParameter("tresce");
                 String OdpAe = request.getParameter("OdpAe");
                 String OdpBe = request.getParameter("OdpBe");
                 String OdpCe = request.getParameter("OdpCe");
                 String OdpDe = request.getParameter("OdpDe"); 
                 String PoprawnaOdpe = request.getParameter("PoprawnaOdpe");
                 String obraze = "";
                 if(request.getParameter("obraze")!=null){
                     obraze = request.getParameter("obraze");
                 }else{
                     obraze ="NULL";
                 }
                 {
             try {
                 PreparedStatement stupdate = con.prepareStatement(sqlu);
                 stupdate.setString(1, tresce);
                 stupdate.setString(2, obraze);
                 stupdate.setString(3, OdpAe);
                 stupdate.setString(4, OdpBe);
                 stupdate.setString(5, OdpCe);
                 stupdate.setString(6, OdpDe);
                 stupdate.setString(7, PoprawnaOdpe);
                 stupdate.setString(8,tresce);
                 stupdate.executeUpdate();
             } catch (SQLException ex) {
                 Logger.getLogger(ExamFunction.class.getName()).log(Level.SEVERE, null, ex);
             }
                 }
           request.getRequestDispatcher("e?id="+EXAMID).forward(request, response);   
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
