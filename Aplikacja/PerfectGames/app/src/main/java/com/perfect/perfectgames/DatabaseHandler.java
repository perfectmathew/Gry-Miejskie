package com.perfect.perfectgames;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler  extends AsyncTask<Void, Void, Void> {

   public final String connectionURL = "jdbc:mysql://192.168.1.10:3308/podchody?autoReconnect=true&useSSL=false";
   String error = "";
   String Con = "";
    Connection con = null;
    @Override
    protected Void doInBackground(Void... voids) {
    connect();
    return null;
    }
    @Override
    protected void onPostExecute(Void avoid){
        if (!error.equals("")) Log.d("ERROR",error);
        Log.d("LOG",Con);
        super.onPostExecute(avoid);

    }
    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.1.10:3308/podchody?autoReconnect=true&useSSL=false", "perfect", "root");
        } catch (SQLException ex) {
            ex.printStackTrace();
        };
        Log.v("DB","Połączono");
    }
    public boolean GameInject(String code){
        boolean found = false;
        connect();
        String SQL = "Select * from players";
return found;
    }

}
