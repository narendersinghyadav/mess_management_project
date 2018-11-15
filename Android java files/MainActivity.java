package com.example.mnit.mess;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
   public String std;
    public static String getMd5(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final EditText sid=(EditText)findViewById(R.id.sid);
        final EditText pass=(EditText)findViewById(R.id.password);
        final Button btn=(Button)findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final String s=sid.getText().toString().trim();
                String p=pass.getText().toString().trim();
                if(s.equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter student id", Toast.LENGTH_SHORT).show();
                }
                else if(p.equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                }
                else {
                   String pa=getMd5(p);
                   std=s;
                    new AsyncLogin().execute(s,pa);
                }
            }
        });
    }
    private class AsyncLogin extends AsyncTask<String,String,String>{
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
        HttpURLConnection conn;
        URL url=null;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                //url=new URL("http://10.0.2.2/test-android/login.inc.php");
                url=new URL("http://04218040.ngrok.io/test-android/login.inc.php");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "exception";
            }
            try {
                conn =(HttpURLConnection)url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                //Append parameter to URL
                Uri.Builder builder=new Uri.Builder().appendQueryParameter("student_id",params[0])
                        .appendQueryParameter("password",params[1]);
                String query=builder.build().getEncodedQuery();
                OutputStream os=conn.getOutputStream();
                BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();
            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            }
            try {
                int response_code=conn.getResponseCode();
                if(response_code==HttpURLConnection.HTTP_OK){
                    InputStream input=conn.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(input));
                    StringBuilder result=new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        result.append(line);
                    }
                    return (result.toString());
                }
                else{
                    return "unsuccessful";
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            }finally {
                conn.disconnect();
            }
        }
        @Override
        protected void onPostExecute(String result){
            pdLoading.dismiss();
            if(result.equalsIgnoreCase("true")){
                Toast.makeText(MainActivity.this, "successfully login", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,lastActivity.class);
                intent.putExtra("std",std);
                startActivity(intent);
                MainActivity.this.finish();
            }
            else if(result.equalsIgnoreCase("false")){
                Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();

            }
            else if(result.equalsIgnoreCase("exception")||result.equalsIgnoreCase("unsuccessful")){
                Toast.makeText(MainActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
