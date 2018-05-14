package com.wolf.silver.hospitalinformationsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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

public class LabPA extends AppCompatActivity {
    List<Test> tl;
    ListView lv;
    TestAdapter ta;
    String id;
    Button ins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_p);
        lv=findViewById(R.id.lld);
        tl=new ArrayList<>();
        ins=findViewById(R.id.adnw);
        ta=new TestAdapter(this,tl);
        Bundle b=getIntent().getExtras();
        id=b.getString("id");

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LabPA.this,InsertLab.class);
                Bundle b=new Bundle();
                b.putString("id",id);
                i.putExtras(b);
                startActivity(i);
            }
        });

        Test th=new Test("ID","TEST NAME","SPECIMEN","RESULT","REF-RANGE","DATE");
        tl.add(th);
    StringRequest stringRequest = new StringRequest(Request.Method.POST,
            "http://192.168.43.207/HosInf/process_labshow.php", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    Test p1 = new Test(

                            String.valueOf(obj.getInt("lid")),
                            obj.getString("test name"),
                            obj.getString("specimen"),
                            obj.getString("result"),
                            obj.getString("ref range"),
                            obj.getString("date")

                    );
                    //  Toast.makeText(getApplicationContext(),p1.getLid(),Toast.LENGTH_LONG).show();
                    tl.add(p1);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ta.notifyDataSetChanged();
        }


    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(LabPA.this,"Error",Toast.LENGTH_LONG).show();
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

        MySingleton.getInstance(LabPA.this).addToRequestque(stringRequest);
    ta=new TestAdapter(this,tl);
        lv.setAdapter(ta);




}
}
