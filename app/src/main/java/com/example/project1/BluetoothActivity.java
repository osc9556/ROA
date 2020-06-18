package com.example.bluetoothactivity;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;


public class BluetoothActivity extends AppCompatActivity {
    private BluetoothSPP bt;
    static String Data_distance;
    //PUSH object
    final NotificationManager notificationManager = (NotificationManagerBluetoothActivity.this.getSystemService(BluetoothActivity.this.NOTIFICATION_SERVICE);
    final Intent intent = new Intent(BluetoothActivity.this.getApplicationContext(),BluetoothActivity.class);
    final Notification.Builder builder = new Notification.Builder(getApplicationContext());

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = new BluetoothSPP(this); //Initializing
        if (!bt.isBluetoothAvailable()) { //블루투스 사용 불가  //distancebluetooth
            Toast.makeText(getApplicationContext()
                    , "Bluetooth is not available"
                    , Toast.LENGTH_SHORT).show();
            finish();
        }

        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            //데이터 수신

            TextView weight = findViewById(R.id.weight);
            private TextView distance = findViewById(R.id.distance);


            public void onDataReceived(byte[] data, String message) {
                String[] array = message.split(",");
                distance.setText(array[0]);  //distance display
            //    weight.setText(array[1].concat("lbs"));   //weight display
                Data_distance = distance.getText().toString();
                int num = Integer.parseInt(Data_distance);
                if(num <= 20){

                    Toast.makeText(getApplicationContext(),"ARARM",Toast.LENGTH_SHORT).show();
                }


            }
        });

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() { //연결됐을 때
            public void onDeviceConnected(String name, String address) {
                address = "98:D3:91:FD:46:92";
                Toast.makeText(getApplicationContext()
                        , "Connected to " + name + "\n" + address
                        , Toast.LENGTH_SHORT).show();
                TextView connect_text = findViewById(R.id.connect_text);
                connect_text.setText("CONNECTION SUCCESS");
            }

            public void onDeviceDisconnected() { //연결해제
                Toast.makeText(getApplicationContext()
                        , "Connection lost", Toast.LENGTH_SHORT).show();
                TextView connect_text = findViewById(R.id.connect_text);
                connect_text.setText("CONNECTION LOST");
            }

            public void onDeviceConnectionFailed() { //연결실패
                Toast.makeText(getApplicationContext()
                        , "Unable to connect", Toast.LENGTH_SHORT).show();
                TextView connect_text = findViewById(R.id.connect_text);
                connect_text.setText("CONNECTION FAILED");
            }
        });

        Button Conn_bluetooth = findViewById(R.id.Conn_bluetooth); //연결시도
        Conn_bluetooth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (bt.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bt.disconnect();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });

    }//OnCreate END
    public void onDestroy() {
        super.onDestroy();
        bt.stopService(); //블루투스 중지
    }

    public void onStart() {
        super.onStart();
        if (!bt.isBluetoothEnabled()) { //
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        } else {
            if (!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER); //DEVICE_ANDROID는 안드로이드 기기 끼리
                setup();
            }
        }
    }

    public void setup() {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();
            } else {
                Toast.makeText(getApplicationContext()
                        , "Bluetooth was not enabled."
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

}
