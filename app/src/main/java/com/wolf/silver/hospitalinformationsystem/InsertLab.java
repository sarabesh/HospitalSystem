package com.wolf.silver.hospitalinformationsystem;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertLab extends AppCompatActivity {
    Button insi;
    EditText spe, te, re, rfe, da;
    String dspe, dte, dre, drfe, dda;
    AlertDialog.Builder builder;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_lab);
        insi = findViewById(R.id.inse);
        spe = findViewById(R.id.speci);
        te = findViewById(R.id.tn);
        re = findViewById(R.id.res);
        rfe = findViewById(R.id.refe);
        da = findViewById(R.id.datee);
        b = getIntent().getExtras();
        insi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dspe = spe.getText().toString();
                dte = te.getText().toString();
                dre = re.getText().toString();
                drfe = rfe.getText().toString();
                dda = da.getText().toString();

                if (dspe.equals("") || dte.equals("") || dre.equals("") || drfe.equals("") || dda.equals("")) {
                    Toast.makeText(getApplicationContext(),"fill all fields",Toast.LENGTH_LONG).show();

                  //  builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    //    @Override
                    //    public void onClick(DialogInterface dialogInterface, int i) {

                       // }
                   // });
                  //  AlertDialog alertDialog = builder.create(); //create
                  //  alertDialog.show();

                } else {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            "http://192.168.43.207/HosInf/process-Labins.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                String message = jsonObject.getString("message");
                             /*   builder.setTitle("Server response");
                                builder.setMessage(message);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                AlertDialog alertDialog = builder.create(); //create
                                alertDialog.show(); //Show it.

                               // builder.setMessage(message);
                                //displayAlert(code);*/
                                Toast.makeText(getApplicationContext(),"inserting",Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            //The keys must match the keys on $_POST on SSS.
                            params.put("id", b.getString("id"));
                            params.put("spec", dspe);
                            params.put("test", dte);
                            params.put("res", dre);
                            params.put("ref", drfe);
                            params.put("date", dda);

                            return params; //Return the MAP.
                        }
                    };
                    MySingleton.getInstance(InsertLab.this).addToRequestque(stringRequest);
                }
            }
        });

    }



}



