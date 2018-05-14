package com.wolf.silver.hospitalinformationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class display extends AppCompatActivity {
    String reg_url = "http://192.168.43.207/HosInf/process_display.php";
    List<Patient> Plist;
    ListView listView;
    PatientAdapter pa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display);
        listView=findViewById(R.id.hi);
        Plist=new ArrayList<>();
        pa=new PatientAdapter(this,Plist);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, reg_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                    JSONObject obj=array.getJSONObject(i);
                                    if(obj.getString("ward").equals("normal")) {
                                        Patient p1 = new Patient(
                                                obj.getInt("id"),
                                                obj.getString("Name"),
                                                obj.getString("age"),
                                                obj.getString("sex"),
                                                obj.getString("address"),
                                                obj.getString("phone"),
                                                obj.getString("Adate"),
                                                obj.getString("ward")
                                        );
                                        Plist.add(p1);
                                    }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }pa.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        MySingleton.getInstance(display.this).addToRequestque(stringRequest);
        listView.setAdapter(pa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent inti=new Intent(view.getContext(),diagonise.class);
                Patient NowP= (Patient) adapterView.getItemAtPosition(i);
                Bundle b=new Bundle();
                b.putString("name", NowP.getName());
                b.putString("id", String.valueOf(NowP.getId()));
                inti.putExtras(b);
                startActivity(inti);
            }
        });
    }
}

