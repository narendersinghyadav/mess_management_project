package com.example.mnit.mess;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class lastActivity extends AppCompatActivity {
    String sid;
//ssd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        Button btn = (Button) findViewById(R.id.button);
        final Intent intent = getIntent();
        final String student_id = intent.getStringExtra("std");
        sid = student_id;
        new Updatepic().execute(sid);
        new UpdateDate().execute(sid);
        Button button=findViewById(R.id.history);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),history.class);
                intent1.putExtra("student_id",sid);
                startActivity(intent1);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateTask().execute(student_id);
            }
        });
    }

    private class UpdateTask extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        URL url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... param) {
            try {
               //url = new URL("http://10.0.2.2/test-android/update.inc.php");
               url=new URL("http://04218040.ngrok.io/test-android/update.inc.php");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "exception";
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
                return "exception";
            }
            try {
                int response_code = conn.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return (result.toString());
                } else {
                    return "unsuccessful";
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            }
        }

        protected void onPostExecute(String result) {
            if (result.equalsIgnoreCase("true")) {
                Toast.makeText(lastActivity.this, "Mess charge is deducted from your account.Login again to update", Toast.LENGTH_LONG).show();
            } else if (result.equalsIgnoreCase("false")) {
                Toast.makeText(lastActivity.this, "Already have today's coupon", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(lastActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();
            }

        }
    }

        private class UpdateDate extends AsyncTask<String, String, String> {
            HttpURLConnection conn;
            URL url;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... param) {
                try {
                    //url = new URL("http://10.0.2.2/test-android/updatedate.php");
                    url=new URL("http://04218040.ngrok.io/test-android/updatedate.php");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return "exception";
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
                    return "exception";
                }
                try {
                    int response_code = conn.getResponseCode();
                    if (response_code == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }
                        return (result.toString());
                    } else {
                        return "unsuccessful";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "exception";
                }
            }

            protected void onPostExecute(String result) {
                if (result.equalsIgnoreCase("false")) {
                    Toast.makeText(lastActivity.this, "Not updated", Toast.LENGTH_LONG).show();
                } else {
                    TextView textView = findViewById(R.id.textView3);
                    textView.setText(sid+" is allowed for "+result);
                }

            }
        }
    private class Updatepic extends AsyncTask<String, String, Bitmap> {
        HttpURLConnection conn;
        URL url;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... param) {
                    Bitmap bitmap=null;
            try {
             //  url = new URL("http://10.0.2.2/test-android/photo.php");
                url=new URL("http://04218040.ngrok.io/test-android/photo.php");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return bitmap;
            }
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("GET");
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
                return bitmap;
            }
            try {
                int response_code = conn.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    String u;
                  //  u="http://10.0.2.2/test-android/photo.php?student_id="+sid;
                    u="http://04218040.ngrok.io/test-android/photo.php?student_id="+sid;
                    URL url1=new URL(u);
                    Bitmap image=BitmapFactory.decodeStream(url1.openConnection().getInputStream());
                    return image;

                } else {
                    return bitmap;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return bitmap;
            }
        }

        protected void onPostExecute(Bitmap image) {
           ImageView imageView=findViewById(R.id.imageView2);
           imageView.setImageBitmap(image);

        }
    }
    }
