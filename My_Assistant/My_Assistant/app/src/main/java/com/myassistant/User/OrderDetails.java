package com.myassistant.User;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;
import android.view.Window;
public class OrderDetails extends AppCompatActivity {
    Button profile,SEND;
    Window window;
    String phonee,hourly_work_id,assistant_id,specialty_id,Specialty_name;
    Intent intent;      SharedPreferences sharedpreferences;

    TextInputEditText description,phone,location,type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        //custome bar with center title
        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_home);
        View view =getSupportActionBar().getCustomView();
        TextView textView= (TextView)view.findViewById(R.id.actionbar_textview);

        textView.setText("Details");
        ImageView imageView= (ImageView)view.findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        sharedpreferences = getSharedPreferences("User_Id", Context.MODE_PRIVATE);
        intent=getIntent();
        phonee=intent.getStringExtra("phone");
        specialty_id=intent.getStringExtra("specialty_id");
        Specialty_name=intent.getStringExtra("Specialty_name");
        hourly_work_id=intent.getStringExtra("hourly_work_id");
        assistant_id=intent.getStringExtra("assistant_id");


        profile=findViewById(R.id.profile);
        SEND=findViewById(R.id.save);
        description=findViewById(R.id.messgae);
        phone=findViewById(R.id.phone);
        location=findViewById(R.id.location);
        type=findViewById(R.id.typeofjob);
        type.setText(Specialty_name);
        phone.setText(phonee);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderDetails.this,AssistantProfileRequest.class);
                intent.putExtra("assistant_id",assistant_id);
                startActivity(intent);
            }
        });
        SEND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(location.getText().toString().isEmpty()){
                    new  AlertDialog.Builder(OrderDetails.this)
                            .setTitle("Location Can't Empty")
                            .setMessage("")

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.icon_error)
                            .show();
                    return;
                }
                sendrequest();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public  void sendrequest(){
        // Create a String request
        // using Volley Library

        String url = "https://myassistant.jaml46.net/APIs/user/add_req_assistant?user_id="+sharedpreferences.getString("userid","")+"&assistant_id="+assistant_id+"&description="+description.getText().toString()+"&whatsapp_phone="+phonee+"&location="+location.getText().toString()+"&specialty_id="+specialty_id+"&hourly_work_id="+hourly_work_id;

        StringRequest request
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                        // Handle the JSON object and
                        // handle it inside try and catch
                        try {


                            // Creating object of JSONObject
                            JSONObject jsonObject
                                    = new JSONObject(response);

                            System.out.println(jsonObject);

                            if(Integer.parseInt(jsonObject.getString("data"))==1){
                                description.setText("");
                                location.setText("");
                                phone.setText("");
                                new AlertDialog.Builder(OrderDetails.this)
                                        .setTitle("Success Send Request")
                                        .setMessage("")

                                        // Specifying a listener allows you to take an action before dismissing the dialog.
                                        // The dialog is automatically dismissed when a dialog button is clicked.
                                        .setPositiveButton("Complete", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .setIcon(R.drawable.icon_success)
                                        .show();
                            }
                            else{
                                new  AlertDialog.Builder(OrderDetails.this)
                                        .setTitle("Something Error")
                                        .setMessage("Please Try Again Later")

                                        // Specifying a listener allows you to take an action before dismissing the dialog.
                                        // The dialog is automatically dismissed when a dialog button is clicked.
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .setIcon(R.drawable.icon_error)
                                        .show();
                            }




                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        RequestQueue requestQueue
                = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}