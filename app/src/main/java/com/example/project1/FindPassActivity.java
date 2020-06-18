package com.example.project1;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FindPassActivity extends AppCompatActivity {

    String myJSON;
    String PW;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_PW = "userPassword";

    JSONArray User_PW = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pass);
        final EditText Phonenum = (EditText) findViewById(R.id.Phonenum2);
        final Button Find_Button = (Button) findViewById(R.id.Find_button2);

        Find_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userPhonenum = Phonenum.getText().toString();
                getData("http://15.164.233.187/findPW.php?userPhonenum=" + userPhonenum);

            }
        });
    }

    protected void showData() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            User_PW = jsonObj.getJSONArray(TAG_RESULTS);
            for (int i = 0; i < User_PW.length(); i++) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                JSONObject c = User_PW.getJSONObject(i);
                PW = c.getString(TAG_PW);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(FindPassActivity.this);
        builder.setTitle("");
        builder.setMessage("비밀번호는 " + PW + "입니다.");
        builder.setNegativeButton("닫기", null);
        builder.create().show();
    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showData();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}
