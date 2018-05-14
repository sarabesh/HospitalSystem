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

public class Register extends AppCompatActivity {
    Button reg_bn;
    EditText Name, eage,esex,eaddress,ephone,edate,eward, Password, ConPassword;
    String name, age,sex,address,phone,date,ward, password, conpass;
    AlertDialog.Builder builder;
    String reg_url = "http://192.168.43.207/HosInf/process_register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg_bn = findViewById(R.id.bn_reg);
        Name = findViewById(R.id.reg_name);
        eage = findViewById(R.id.reg_age);
        esex = findViewById(R.id.reg_sex);
        eaddress = findViewById(R.id.reg_address);
        ephone = findViewById(R.id.reg_phone);
        edate=findViewById(R.id.reg_date);
        eward=findViewById(R.id.ward);
        Password = findViewById(R.id.reg_password);
        ConPassword = findViewById(R.id.reg_con_password);
        builder = new AlertDialog.Builder(Register.this);
        reg_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = Name.getText().toString();
                password = Password.getText().toString();
                age=eage.getText().toString();
                sex=esex.getText().toString();
                address=eaddress.getText().toString();
                date=edate.getText().toString();
                phone=ephone.getText().toString();
                ward=eward.getText().toString();
                conpass = ConPassword.getText().toString();
                if (name.equals("")||age.equals("")||sex.equals("")||address.equals("")||phone.equals("")||date.equals("")||password.equals("")||ward.equals("")||conpass.equals("")){
                    builder.setTitle("Error");
                    builder.setMessage("Please fill up all the fields.");
                    displayAlert("input_error");
                } else{

                    if (!(password.equals(conpass))){
                        builder.setTitle("Error");
                        builder.setMessage("Passwords do not match.");
                        displayAlert("input_error");
                    }else {

                        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                                reg_url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    String code = jsonObject.getString("code");
                                    String message = jsonObject.getString("message");


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> params = new HashMap<String, String>();
                                //The keys must match the keys on $_POST on SSS.
                                params.put("name",name);
                                params.put("pass",password);
                                params.put("age",age);
                                params.put("sex",sex);
                                params.put("address",address);
                                params.put("phone",phone);
                                params.put("ward",ward);
                                params.put("date",date);

                                return params; //Return the MAP.
                            }
                        };
                        MySingleton.getInstance(Register.this).addToRequestque(stringRequest);
                    }
                }
            }
        });
    }

    public void displayAlert(final String code){
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (code == "input_error"){
                    Password.setText("");
                    ConPassword.setText("");
                }
                else if (code.equals("reg_success")){
                    finish();
                }
                else if (code.equals("reg_failed")){

                    Name.setText("");
                    eage.setText("");
                    esex.setText("");
                    eaddress.setText("");
                    ephone.setText("");
                    edate.setText("");
                    eward.setText("");
                    Password.setText("");
                    ConPassword.setText("");
                }
            }
        });
        //Display the alert dialog.
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
