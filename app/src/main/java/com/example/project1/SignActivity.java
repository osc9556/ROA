package com.example.project1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SignActivity extends AppCompatActivity {

    private Button sign_button;
    private AlertDialog dialog;
    private EditText editTextId;
    private EditText editTextPw;
    private EditText editTextName;
    private EditText editTextphone;
    private EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        editTextId = (EditText) findViewById(R.id.idtext);
        editTextPw = (EditText) findViewById(R.id.pwtext);
        editTextName=(EditText) findViewById(R.id.nametext);
        editTextphone=(EditText) findViewById(R.id.phtext);
        editTextEmail= (EditText) findViewById(R.id.etext);


    }

    public void insert(View view) {
        String Id = editTextId.getText().toString();
        String Pw = editTextPw.getText().toString();
        String Name = editTextName.getText().toString();
        String Phone = editTextphone.getText().toString();
        String Email = editTextEmail.getText().toString();


        insertoToDatabase(Id, Pw, Name, Phone, Email);
    }
    private void insertoToDatabase(String Id, String Pw, String Name, String Phone, String Email) {
        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SignActivity.this, "Please Wait", null, true, true);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(String... params) {

                try {
                    String Id = (String) params[0];
                    String Pw = (String) params[1];
                    String Name = (String) params[2];
                    String Phone = (String) params[3];
                    String Email = (String) params[4];

                    String link = "http://15.164.233.187/phpmyadmin/usersign.php";
                    String data = URLEncoder.encode("Id", "UTF-8") + "=" + URLEncoder.encode(Id, "UTF-8");
                    data += "&" + URLEncoder.encode("Pw", "UTF-8") + "=" + URLEncoder.encode(Pw, "UTF-8");
                    data += "&" + URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(Pw, "UTF-8");
                    data += "&" + URLEncoder.encode("Phone", "UTF-8") + "=" + URLEncoder.encode(Pw, "UTF-8");
                    data += "&" + URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode(Pw, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }
            }
        }
        InsertData task = new InsertData();
        task.execute(Id, Pw ,Name,Phone,Email);
    }
}