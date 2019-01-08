package com.example.morm4.stockvalue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Application;
import android.widget.Toast;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://10.0.2.2:3000";

    private Socket sSocket;

    String stockType;
    Button sendButt;
    EditText stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        {
            try {
                sSocket = IO.socket(URL);
                Log.d("hello", "Connection Done");
                sSocket.connect();
                sSocket.on("connection", sendStock);

            } catch (URISyntaxException e) {
                Log.d("hello", "Unable to connect");
                throw new RuntimeException(e);
            }
        }
        Toast.makeText(getApplicationContext(),"Live Stock Exchange", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stock = (EditText) findViewById(R.id.editText);
        sendButt = (Button) findViewById(R.id.buttonSend);

        sendButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockType = stock.getText().toString();
                sSocket.emit("stock type", stockType);


            }
        });

    }
    private Emitter.Listener sendStock = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String price;
                    try {
                        price = data.getString("price");
                        Log.d("hello", "server update price");
                        Toast.makeText(getApplicationContext(),"update", Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        return;
                    }
                    }

                });
            }
        };
    }