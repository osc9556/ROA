package com.example.project1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class FindIdActivity extends AppCompatActivity {

    private Button Find_button;
    private String a = "";

    phpdo task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);

        final EditText Email = (EditText)findViewById(R.id.Email);

        Find_button = findViewById(R.id.Find_button); //로그아웃 버튼
        Find_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String E_mail = Email.getText().toString();

                task = new phpdo();
                task.execute(E_mail);

                AlertDialog.Builder builder = new AlertDialog.Builder(FindIdActivity.this);

            }
        });
    }

    private class phpdo extends AsyncTask<String,Void,String>{

        protected void onPreExecute(){

        }
        @Override
        protected String doInBackground(String... arg0) {
            try{
                String email = arg0[0];

                String link = "http://15.164.233.187/findID.php?userEmail=" + email;
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while((line = in.readLine()) != null){
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result){
            a = result;
        }
    }
}
