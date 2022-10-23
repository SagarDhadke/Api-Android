package net.uniquecomputer.apiserver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SubscriptionApi extends AppCompatActivity {

    TextView id,uid,plan,status,payment_method,subscription_term,payment_term;

    String url = "https://random-data-api.com/api/subscription/random_subscription";
    RequestQueue myRequest;
    TextView textView, EditText;


//    textView = findViewById(r.id.randomid);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_api);

        myRequest = Volley.newRequestQueue(this);

        id = findViewById(R.id.randomid);
        uid = findViewById(R.id.randomuid);
        plan = findViewById(R.id.randomplan);
        status = findViewById(R.id.randomstatus);
        payment_method = findViewById(R.id.randompaym);
        subscription_term = findViewById(R.id.randomsub);
        payment_term = findViewById(R.id.randompayte);

        findViewById(R.id.fetch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

    }

    private void getData() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    id.setText(response.get("id").toString());
                    uid.setText(response.get("uid").toString());
                    plan.setText(response.get("plan").toString());
                    status.setText(response.get("status").toString());
                    payment_method.setText(response.get("payment_method").toString());
                    subscription_term.setText(response.get("subscription_term").toString());
                    payment_term.setText(response.get("payment_term").toString());



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