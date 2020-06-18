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


public class FindIdActivity extends AppCompatActivity {

    String myJSON;
    String ID;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "userID";

    JSONArray User_ID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);
        final EditText Phonenum = (EditText) findViewById(R.id.Phonenum);
        final Button Find_Button = (Button) findViewById(R.id.Find_button);

        Find_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userPhonenum = Phonenum.getText().toString();
                getData("http://15.164.233.187/findID.php?userPhonenum=" + userPhonenum);

            }
        });
    }

    protected void showData() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            //User_ID = jsonObj.getJSONArray(TAG_RESULTS).toString();
           // User_ID = jsonObj.getString(TAG_RESULTS);
            User_ID = jsonObj.getJSONArray(TAG_RESULTS);
            for (int i = 0; i < User_ID.length(); i++) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                JSONObject c = User_ID.getJSONObject(i);
                ID = c.getString(TAG_ID);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(FindIdActivity.this);
        builder.setTitle("");
        builder.setMessage("아이디는 " + ID + "입니다.");
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

