package com.perfect.perfectgames;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.net.ssl.HttpsURLConnection;

public class Login extends AppCompatActivity {
    Connection con = null;
    private Button button;
    private EditText code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        code = findViewById(R.id.playercode);
        button = findViewById(R.id.play);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(code.getText().toString().equals("") || code.getText().toString() == null){
                    Toast.makeText(Login.this, "Nie wpisałeś kodu zaproszenia! Kod zaproszenia został przesłany na twoja skrzynke pocztową!", Toast.LENGTH_LONG).show();
                }else{
                    boolean found = false;
                    String tempimg = "";
                    String tempname = "";
                    //new DatabaseHandler().execute();
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {

                        con = DriverManager.getConnection("jdbc:mysql://192.168.1.10:3308/podchody?autoReconnect=true&useSSL=false", "perfect", "root");
                        PreparedStatement st = con.prepareStatement("SELECT * FROM players INNER JOIN Games ON (players.IDGame = Games.ID) WHERE KodDstepu = ?");
                        st.setString(1,code.getText().toString());
                        ResultSet rs = st.executeQuery();
                       if(rs.next()) {
                           found = true;
                            tempimg = rs.getString("obraz");
                            tempname = rs.getString("Nazwa");
                       } else found = false;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    if(found){
                        showCustomDialog(tempimg,tempname);
                    }else{
                        Toast.makeText(Login.this, "Nie Znaleziono żdanej gry w której uczestniczysz", Toast.LENGTH_LONG).show();
                    }


                }
            }
        });
    }
    void showCustomDialog(String url, String name) {
        final Dialog dialog = new Dialog(Login.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.game_dialog);
        final TextView nameofgame = dialog.findViewById(R.id.nameofgame);
        final ImageView img = dialog.findViewById(R.id.imgOfGame);
        nameofgame.setText(name);
        img.setImageBitmap(getBitmapFromURL(url));
        Button submitButton = dialog.findViewById(R.id.playdialog);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
    public static String getJSON(String url) {
        HttpURLConnection cone = null;
        try {
            URL u = new URL(url);
            cone = (HttpURLConnection) u.openConnection();
            cone.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(cone.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            return sb.toString();


        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (cone != null) {
                try {
                    cone.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

}