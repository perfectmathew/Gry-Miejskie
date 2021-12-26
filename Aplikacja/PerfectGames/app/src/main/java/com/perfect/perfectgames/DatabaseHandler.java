package com.perfect.perfectgames;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler  extends AsyncTask<Void, Void, Void> {

   public final String connectionURL = "jdbc:mysql://192.168.1.10:3308/podchody?autoReconnect=true&useSSL=false";
   String error = "";
   String Con = "";

    @Override
    protected Void doInBackground(Void... voids) {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.1.10:3308/podchody?autoReconnect=true&useSSL=false", "perfect", "root");
        } catch (SQLException ex) {
                ex.printStackTrace();
        };
        Log.v("DB","Połączono");
        String SQl = "SELECT * FROM tasks";
        Statement st = null;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(SQl);
            while (rs.next()){
                Log.v("RS",rs.getString("Tresc"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    return null;
    }
    @Override
    protected void onPostExecute(Void avoid){
        if (!error.equals("")) Log.d("ERROR",error);
        Log.d("LOG",Con);
        super.onPostExecute(avoid);

    }
    public void connect(){


    }

}
