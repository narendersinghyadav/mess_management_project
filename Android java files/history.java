package com.example.mnit.mess;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class history extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent intent=getIntent();
        String string=intent.getStringExtra("student_id");
      new UpdateTask().execute(string);

    }

    private class UpdateTask extends AsyncTask<String, String, ArrayList<String>> {
        HttpURLConnection conn;
        URL url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected  ArrayList<String> doInBackground(String... param) {
            ArrayList<String> strings=new ArrayList<>();
            try {
              //url = new URL("http://10.0.2.2/test-android/updatehistory.php");
               url=new URL("http://04218040.ngrok.io/test-android/updatehistory.php");
            } catch (MalformedURLException e) {
                e.printStackTrace();

               return  strings;
            }
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setConnectTimeout(15000);
                conn.setReadTimeout(10000);
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("student_id", param[0]);
                String query = builder.build().getEncodedQuery();
                OutputStream os = conn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                bufferedWriter.write(query);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                conn.connect();
            } catch (IOException e) {
                e.printStackTrace();

                return  strings;
            }
            try {
                int response_code = conn.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = reader.readLine()) != null) {
                      strings.add(line);
                    }

                    return strings;
                } else {
                    strings.add("exception");
                    return  strings;
                }
            } catch (IOException e) {
                e.printStackTrace();

                return  strings;
            }
        }

        protected void onPostExecute(ArrayList<String > result) {
           if(result.isEmpty()){
               Toast.makeText(getApplicationContext(),"Unsuccessful",Toast.LENGTH_SHORT);
           }
           else{
               ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.list,result);
               ListView listView =findViewById(R.id.list);
               listView.setAdapter(arrayAdapter);
           }

        }
    }
}