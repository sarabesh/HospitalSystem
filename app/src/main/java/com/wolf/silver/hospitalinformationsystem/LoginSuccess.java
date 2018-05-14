package com.wolf.silver.hospitalinformationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class LoginSuccess extends AppCompatActivity {
    TextView username,password,agel,sexl,addressl,phonel,wardl,datel,dia;
    String reg_url = "http://192.168.43.207/HosInf/process_display.php";
    Button v;
    String id;
   // Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        dia=findViewById(R.id.diag);
        v=findViewById(R.id.view);
        //b=getIntent()
        username =  findViewById(R.id.username);
        password =  findViewById(R.id.password);
        agel=findViewById(R.id.age);
        sexl=findViewById(R.id.sex);
        addressl=findViewById(R.id.address);
        phonel=findViewById(R.id.phone);
        wardl=findViewById(R.id.login_ward);
        datel=findViewById(R.id.date);

       final Bundle bundle = getIntent().getExtras();
        id=bundle.getString("id");
        username.setText("Welcome " + bundle.getString("un"));
        password.setText("Password : " + bundle.getString("pwd"));
        agel.setText("Age : " + bundle.getString("age"));
        sexl.setText("Sex : " + bundle.getString("sex"));
        addressl.setText("Address : " + bundle.getString("addr"));
        phonel.setText("Ph no : " + bundle.getString("phn"));
        wardl.setText("Ward : " + bundle.getString("ward"));
        datel.setText("Date : " + bundle.getString("date"));


       v.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent inti=new Intent(view.getContext(),LabRec.class);

               Bundle b=new Bundle();
               b.putString("name", bundle.getString("un"));
               b.putString("id", id);
               inti.putExtras(b);
               startActivity(inti);
           }
       });

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST,
                "http://192.168.43.207/HosInf/process-reg.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String code = jsonObject.getString("code");

                    if (code.equals("new")){

                    } else{

                        dia.setText(jsonObject.getString("dia"));
                       // da.setText(jsonObject.getString("date"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginSuccess.this,"Error",Toast.LENGTH_LONG).show();
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
        MySingleton.getInstance(LoginSuccess.this).addToRequestque(stringRequest2);














    }
}