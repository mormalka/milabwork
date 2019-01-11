package com.example.morm4.stockvalue;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URISyntaxException;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static final String URI = "http://10.0.2.2:3000";

    private Socket sSocket;
    Spinner spinner;
    String stockType;
    Button sendButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Client's attempt connecting the server
        try {
            sSocket = IO.socket(URI);
            Log.d("hello", "Connection Done");
            sSocket.connect();
            sSocket.on("stock type", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            JSONObject data = (JSONObject) args[0];
                            String price;
                            try {
                                price = data.getString("price");
                                Log.d("hello", "got server data");
                                //display the stock value on bottom of the screen
                                Toast.makeText(getApplicationContext(),"Current value: " + price, Toast.LENGTH_LONG).show();
                                MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.kaching);
                                ring.start();
                            } catch (JSONException e) {
                                return; }
                        }

                    });
                }
            });

            } catch (URISyntaxException e) {
                Log.d("hello", "Unable to connect");
                throw new RuntimeException(e); }

        Toast.makeText(getApplicationContext(), "Live Stock Exchange", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting up the stocks spinner
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.stocks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        sendButt = (Button) findViewById(R.id.buttonSend);
        sendButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send stock name to server
                sSocket.emit("stock type", stockType);
            }
        });


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        stockType = parent.getItemAtPosition(position).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent){
    }
}