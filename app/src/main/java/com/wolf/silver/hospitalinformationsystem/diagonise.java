package com.wolf.silver.hospitalinformationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class diagonise extends AppCompatActivity {
    TextView t;
    EditText e,da;
     String id;

    String reg_url2="http://192.168.43.207/HosInf/process-reg.php";
    Button sub,lb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagonise);
        Bundle b=getIntent().getExtras();
        lb=findViewById(R.id.lb);
        id=b.getString("id");
        e=findViewById(R.id.diago);
         t=findViewById(R.id.disa);
        t.setText(b.getString("name"));
        sub=findViewById(R.id.diasub);
        da=findViewById(R.id.date1);

        lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(diagonise.this,LabRec.class);
                        Bundle b=new Bundle();
                b.putString("id",id);
                i.putExtras(b);
                startActivity(i);
            }
        });


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                reg_url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String code = jsonObject.getString("code");

                    if (code.equals("new")){

                    } else{

                       e.setText(jsonObject.getString("dia"));
                       da.setText(jsonObject.getString("date"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(diagonise.this,"Error",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }){


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("id",(id));

                return params;
            }
        };
        MySingleton.getInstance(diagonise.this).addToRequestque(stringRequest);













        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String pid = id;
                final String diago = e.getText().toString();
                final String date=da.getText().toString();


                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            "http://192.168.43.207/HosInf/process-diagnew.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");

                                if (code.equals("updated")){

                                Toast.makeText(getApplicationContext(),"updated",Toast.LENGTH_LONG).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(diagonise.this,"Error",Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }
                    }){


                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> params = new HashMap<String, String>();
                            params.put("id", pid);
                            params.put("dia", diago);
                            params.put("date",date);
                            return params;
                        }
                    };

                    MySingleton.getInstance(diagonise.this).addToRequestque(stringRequest);


            }
        });
            }



}

