package com.example.identificadorderaca;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RacasActivity extends AppCompatActivity {


    private TextView lblRaca;
    private ListView listView;
    private ImageView imgView;
    private String subRaca;
    private String raca;
    private String fff = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_racas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Escolha a sub-raça");

        Intent intent = getIntent();
        raca = intent.getStringExtra("raca");
        subRaca = intent.getStringExtra("subRaca");
        String urlImg = intent.getStringExtra("imgRaca");


        //TextView
        lblRaca = findViewById(R.id.lblRaca);
        lblRaca.setText(raca);

        //ImageView
        imgView = findViewById(R.id.imgRaca);

        Picasso.get().load(urlImg).into(imgView);

        listView = findViewById(R.id.listaSubRaca);

        Log.e("asdads",subRaca);

        ArrayList<String> subRacas = new ArrayList<>();

        String[] racas = subRaca.split(",");

        for(int i = 0; i<racas.length;i++){
            subRacas.add(racas[i]);
            Log.e("RAÇAS",racas[i]);
        }

        JsonObjectRequest requestImg = new JsonObjectRequest(Request.Method.GET, "https://dog.ceo/api/breed/"+raca+"/images/random", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    fff = response.get("message").toString();
                    Picasso.get().load(fff).into(imgView);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ErroIMG",error.getMessage());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(requestImg);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,subRacas);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String subRaca = listView.getItemAtPosition(position).toString();

                Intent resultIntent = new Intent(RacasActivity.this,ImagemActivity.class);
                resultIntent.putExtra("subRaca",subRaca);
                resultIntent.putExtra("raca",raca);
                resultIntent.putExtra("verif",true);
                startActivity(resultIntent);

            }
        });


    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(RacasActivity.this, MainActivity.class));
        finish();
    }



}
