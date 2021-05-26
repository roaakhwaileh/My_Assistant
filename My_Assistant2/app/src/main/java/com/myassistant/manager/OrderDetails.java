package com.myassistant.manager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.myassistant.manager.Model.jobrequestitem;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderDetails extends AppCompatActivity {
    Button profile,accept,whatsapp;
    TextInputEditText location,descripption;
    String req_assistant_id,phone,user_id;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
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
        profile=findViewById(R.id.profile);
        accept=findViewById(R.id.accept);
        location=findViewById(R.id.location);
        descripption=findViewById(R.id.messgae);
        whatsapp=findViewById(R.id.whatsapp);
        location.setText(intent.getStringExtra("location"));
        descripption.setText(intent.getStringExtra("descripption"));
        phone=intent.getStringExtra("phone");
        user_id=intent.getStringExtra("user_id");
        req_assistant_id=intent.getStringExtra("req_assistant_id");
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderDetails.this,JObRequestuserProfile.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating new intent
                Intent intent
                        = new Intent(Intent.ACTION_SEND);

                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");

                // Give your message here
                intent.putExtra(
                        Intent.EXTRA_TEXT,
                        "\n" +
                                "Hello ..\n" +
                                "I am sending you through the My Assistant app");

                // Checking whether Whatsapp
                // is installed or not
                if (intent
                        .resolveActivity(
                                getPackageManager())
                        == null) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Please install whatsapp first.",
                            Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                // Starting Whatsapp
                startActivity(intent);
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptedorder(req_assistant_id);
            }
        });

    }
       @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private void acceptedorder(String  req_assistant_id)
    {




        // Create a String request
        // using Volley Library

        String url = "https://myassistant.jaml46.net/APIs/assistant/accept_button?req_assistant_id="+req_assistant_id;

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
                            JSONObject jsonObject=new JSONObject(response);
                            if(Integer.parseInt(jsonObject.getString("data"))==1){

                                new AlertDialog.Builder(OrderDetails.this)
                                        .setTitle("Success Accepted ")
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