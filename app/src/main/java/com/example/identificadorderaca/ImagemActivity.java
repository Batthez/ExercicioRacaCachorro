package com.example.identificadorderaca;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
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

public class ImagemActivity extends AppCompatActivity {

    private ImageView imgView;
    private RequestQueue queue;
    private TextView lblNomeDog;
    private final int SUB_RACA = 1000;
    private String subRaca = "";
    private String raca;
    private String url;
    private boolean verif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Doguinhos fofos");

        lblNomeDog = findViewById(R.id.lblNomeDog);

        Intent intent = getIntent();
        verif = intent.getBooleanExtra("verif",false);
        raca = intent.getStringExtra("raca");

        imgRequest();

    }


    private void imgRequest(){

        Intent intent = getIntent();


        if(verif){

            subRaca = intent.getStringExtra("subRaca");
            url = "https://dog.ceo/api/breed/"+raca+"/"+subRaca+"/images/random";
            lblNomeDog.setText(raca+" "+subRaca);

        }else{
            url = "https://dog.ceo/api/breed/"+raca+"/images/random";
            lblNomeDog.setText(raca);
        }
        imgView = findViewById(R.id.imgDog);

        //Utilizando o Volley para requisitar a API
        queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String urlImg = response.get("message").toString();
                    Picasso.get().load(urlImg).into(imgView);

                    salvarUltimaImg(urlImg);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);

    }

    @Override
    public void onBackPressed(){
            startActivity(new Intent(ImagemActivity.this, MainActivity.class));
            finish();
    }

    private void salvarUltimaImg(String url){

        SharedPreferences sharedPreferences = getSharedPreferences("dog_preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("url",url);
        editor.apply();

    }

}
