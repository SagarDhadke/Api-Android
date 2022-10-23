package net.uniquecomputer.apiserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Cryto extends AppCompatActivity {

    TextView id,uid,coni_name,acronym;
//    ImageView imageView;
    String url = "https://random-data-api.com/api/crypto_coin/random_crypto_coin";


    //you send request to the server "RequestQueue" help to execute ...
    RequestQueue myRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryto);

        myRequest = Volley.newRequestQueue(this);

        id = findViewById(R.id.CryptoId);
        uid = findViewById(R.id.CryptoUid);
        coni_name = findViewById(R.id.coin_name);
        acronym = findViewById(R.id.acronym);
//        imageView = findViewById(R.id.CrytoImg);


        findViewById(R.id.Cbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { getData(); }

        });

    }

    private void getData() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.d("api",response.toString());

                            id.setText(response.get("id").toString());
                            uid.setText(response.get("uid").toString());
                            coni_name.setText(response.get("coin_name").toString());
                            acronym.setText(response.get("acronym").toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });

        myRequest.add(request);
    }
}