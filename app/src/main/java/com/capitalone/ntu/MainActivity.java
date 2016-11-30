package com.capitalone.ntu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private  Button requestButton;
    private TextView idTextView;
    private TextView nameTextView;
    private TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://api.myjson.com/bins/3i9zi";

        // Instantiate the RequestQueue. Only once per application.
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Initialise the Views
        requestButton = (Button) findViewById(R.id.request_button);

        idTextView = (TextView) findViewById(R.id.user_id_textview);
        nameTextView = (TextView) findViewById(R.id.user_name_textview);
        emailTextView = (TextView) findViewById(R.id.user_email_textview);

        // Create the request object. This object contains information about the request like
        // type of method (GET, PUT, POST...), the URL, and the listeners. These listeners specify
        // what the application should do when the request succeed or fails
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //Network request succeed, your code goes here
                Log.d("DEBUGGING", "onResponse: " + response.toString());

                try {

                    idTextView.setText(response.getString("id"));
                    nameTextView.setText(response.getString("name"));
                    emailTextView.setText(response.getString("email"));

                } catch (JSONException e) {
                    idTextView.setText("Error");
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Network request failure, your code goes here
                Log.d("DEBUGGING", "onErrorResponse: " + error.toString());
            }
        });


        // Create a listener for the button.
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // By adding the request object to the requestQueue we are going to trigger the request
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}
