package net.uniquecomputer.apiserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView idTxt,incomeTxt,ageTxt,salary;

    //fetch data from this url
    String url = "https://biosagar.000webhostapp.com/";

    //you send request to the server "RequestQueue" help to execute ...
    RequestQueue myRequest;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        button.findViewById(R.id.sub);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,SubscriptionApi.class);
//                startActivity(intent);
//            }
//        });

        findViewById(R.id.sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SubscriptionApi.class);
                startActivity(intent);
            }
        });

        btn = findViewById(R.id.refresh);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Cryto.class);
                startActivity(intent);
            }
        });


        //initialise myRequest
        myRequest = Volley.newRequestQueue(this);

        // this are all ids like textview Button etc..
        idTxt = findViewById(R.id.uid);
        incomeTxt = findViewById(R.id.income);
        ageTxt = findViewById(R.id.uage);
        salary = findViewById(R.id.salary);

        //This helps to click the button and get the data from the api... "gteData"
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getData();
            }
        });

    }

    private void getData() {

        // Create a object fro Json "Data is an Json From"
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,     // "JsonObjectRequest" For single object
                new Response.Listener<JSONObject>() {                                                  // jsonRequest Null "You are creating new Request So You pass null"
            @Override
            public void onResponse(JSONObject response) {

                try {
                    idTxt.setText(response.get("id").toString());              //idTxt is ou fot the get "id" json data from the server...
                    incomeTxt.setText(response.get("income").toString());
                    ageTxt.setText(response.get("age").toString());
                    salary.setText(response.get("monthly_salary").toString());

                                //try catch meaning is app not crash

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();              // "printStackTrace" you can see the error in the logcat

            }
        });


        myRequest.add(request);

    }
}