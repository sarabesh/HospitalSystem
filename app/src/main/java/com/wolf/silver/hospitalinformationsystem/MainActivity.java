package com.wolf.silver.hospitalinformationsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button login_button;
    EditText UserName, Password,docname,docpswd;
    String username, password,dname,dpass;
    String login_url = "http://192.168.43.207/HosInf/process_login.php";
    String Doc_url="http://192.168.43.207/HosInf/process_doctor.php";
    AlertDialog.Builder builder;
    Button doc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doc=findViewById(R.id.doc);
        textView = findViewById(R.id.reg_txt);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });

        builder = new AlertDialog.Builder(MainActivity.this);
        login_button = (Button) findViewById(R.id.bn_login);
        UserName = (EditText) findViewById(R.id.login_name);
        Password = (EditText) findViewById(R.id.login_password);
        docname=findViewById(R.id.doctor_name);
        docpswd=findViewById(R.id.doctor_password);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = UserName.getText().toString();
                password = Password.getText().toString();
                if (username.equals("")||password.equals("")){
                    builder.setTitle("Error");

                    displayAlert("Enter a valid username or password.");
                } else{

                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            login_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");

                                if (code.equals("login_failed")){
                                    builder.setTitle("Login failed");
                                    displayAlert(jsonObject.getString("message"));
                                } else{

                                    Intent intent = new Intent(MainActivity.this,
                                            LoginSuccess.class);

                                    Bundle bundle = new Bundle();
                                    bundle.putString("id",jsonObject.getString("id"));
                                    bundle.putString("un", jsonObject.getString("uname"));
                                    bundle.putString("pwd", jsonObject.getString("pass"));
                                    bundle.putString("age", jsonObject.getString("age"));
                                    bundle.putString("sex", jsonObject.getString("sex"));//Attact bundle to intent
                                    bundle.putString("addr", jsonObject.getString("address"));
                                    bundle.putString("phn", jsonObject.getString("phone"));
                                    bundle.putString("date", jsonObject.getString("date"));
                                    bundle.putString("ward", jsonObject.getString("ward"));
                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }
                    }){


                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> params = new HashMap<String, String>();
                            params.put("un", username);
                            params.put("pwd", password);
                            return params;
                        }
                    };

                    MySingleton.getInstance(MainActivity.this).addToRequestque(stringRequest);

                }
            }
        });

        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dname = docname.getText().toString();
                dpass = docpswd.getText().toString();
                if (dname.equals("")||dpass.equals("")){
                    builder.setTitle("Error");

                    displayAlert("Enter a valid username or password.");
                } else{

                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            Doc_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                String pro=jsonObject.getString("prof");
                                if (code.equals("login_failed")){
                                    builder.setTitle("Login failed");
                                    displayAlert(jsonObject.getString("message"));
                                } else{
                                    if(pro.equals("doctornor")){
                                    Intent intent = new Intent(MainActivity.this,
                                            display.class);

                                    Bundle bundle = new Bundle();
                                    bundle.putString("un", jsonObject.getString("uname"));

                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                }

                                else if(pro.equals("doctorICU")){
                                        Intent intent = new Intent(MainActivity.this,
                                                ICUward.class);

                                        Bundle bundle = new Bundle();
                                        bundle.putString("un", jsonObject.getString("uname"));

                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }

                                else if(pro.equals("doctorout")){
                                        Intent intent = new Intent(MainActivity.this,
                                                Out.class);

                                        Bundle bundle = new Bundle();
                                        bundle.putString("un", jsonObject.getString("uname"));

                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                 else if(pro.equals("lab")){
                                        Intent intent = new Intent(MainActivity.this,
                                                LabView.class);

                                        Bundle bundle = new Bundle();
                                        bundle.putString("un", jsonObject.getString("uname"));

                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }
                    }){


                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> params = new HashMap<String, String>();
                            params.put("un", dname);
                            params.put("pwd", dpass);
                            return params;
                        }
                    };

                    MySingleton.getInstance(MainActivity.this).addToRequestque(stringRequest);

                }
            }
        });




    }

    public void displayAlert(String message){
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                UserName.setText(""); Password.setText("");
            }
        });
        AlertDialog alertDialog = builder.create(); //create
        alertDialog.show(); //Show it.
    }


}
