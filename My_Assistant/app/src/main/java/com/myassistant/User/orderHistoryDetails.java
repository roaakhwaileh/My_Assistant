package com.myassistant.User;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
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

public class orderHistoryDetails extends AppCompatActivity {
    Intent intent;
    Button profile; Button Report;    SharedPreferences sharedpreferences;
    String phonee,location,assistant_id,description,Specialty_name;
    TextInputEditText descriptiontx,phonetx,locationtx,typetx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_details);
        //custome bar with center title

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
        intent=getIntent();
        sharedpreferences = getSharedPreferences("User_Id", Context.MODE_PRIVATE);
        Report=findViewById(R.id.report);
        descriptiontx=findViewById(R.id.messgae);
        phonetx=findViewById(R.id.phone);
        locationtx=findViewById(R.id.location);
        typetx=findViewById(R.id.typeofjob);
        profile=findViewById(R.id.profile);
        assistant_id=intent.getStringExtra("assistant_id");
        phonetx.setText(intent.getStringExtra("phone"));
        descriptiontx.setText(intent.getStringExtra("description"));
        locationtx.setText(intent.getStringExtra("location"));
        typetx.setText(intent.getStringExtra("jobtype"));
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(orderHistoryDetails.this,AssistantProfile.class);
                intent.putExtra("assistant_id",assistant_id);
                startActivity(intent);
            }
        });
        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertadd = new AlertDialog.Builder(orderHistoryDetails.this);
                LayoutInflater factory = LayoutInflater.from(orderHistoryDetails.this);
                final View view = factory.inflate(R.layout.reportlayout, null);
                Button send=view.findViewById(R.id.send);
                TextInputEditText text;
                RatingBar ratingBar;
                text=view.findViewById(R.id.text);
                alertadd.setView(view);
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(text.getText().toString().isEmpty()){
                            new  AlertDialog.Builder(orderHistoryDetails.this)
                                    .setTitle("Text Can't be Empty")
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


                        sendreport(text);

                    }
                });
                alertadd.show();
            }
        });
    }
    public  void sendreport(TextInputEditText text){


        String url = "https://myassistant.jaml46.net/APIs/assistant/add_report?assistant_id="+9+"&user_id="+sharedpreferences.getString("userid","")+"&text="+text.getText().toString();

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
                                text.setText("");
                                new AlertDialog.Builder(orderHistoryDetails.this)
                                        .setTitle("Success Report ")
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
                                new  AlertDialog.Builder(orderHistoryDetails.this)
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