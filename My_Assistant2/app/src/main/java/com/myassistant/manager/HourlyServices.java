package com.myassistant.manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.myassistant.manager.Model.HoureRate;
import com.myassistant.manager.Model.Type;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import android.view.Window;
public class HourlyServices extends AppCompatActivity {
    TypeAdapter typeAdapter;
    RecyclerView recyclerView;
    ArrayList<HoureRate> typelisthourly;
    ArrayList<Type> typelistt;
    ArrayList<String> typelisttid;
    SharedPreferences sharedpreferences;
    TextInputEditText priceti;
    String id;
    String selected_index;
    Button save;
    Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_rate);
        //custome bar with center title
        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        getSupportActionBar().setCustomView(R.layout.action_bar_home);
        View view =getSupportActionBar().getCustomView();
        TextView textView= (TextView)view.findViewById(R.id.actionbar_textview);
        textView.setText("Hourly Rate");
        ImageView imageView= (ImageView)view.findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        sharedpreferences = getSharedPreferences("Assistant_Id", Context.MODE_PRIVATE);
        recyclerView = findViewById(R.id.recyclerView);
        priceti = findViewById(R.id.pricee);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditHourly();
            }
        });
    }
    public void filllist( ArrayList<Type> typelist){
        System.out.println(typelist.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

        typeAdapter = new TypeAdapter( typelist,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(typeAdapter);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder>{
        ArrayList<Type> typelist;
        Context context;

        // RecyclerView recyclerView;
        public TypeAdapter(ArrayList<Type> typelist,Context context) {
            this.typelist = typelist;
            this.context = context;
        }
        @Override
        public TypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.typeitem, parent, false);
            TypeAdapter.ViewHolder viewHolder = new TypeAdapter.ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TypeAdapter.ViewHolder holder, int position) {
            holder.type.setText(typelist.get(position).getSpecialty_name());
            selected_index=typelist.get(position).getSpecialty_id();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(typelist.get(position).getSpecialty_id()+"  " );
                    if(typelisttid.contains(typelist.get(position).getSpecialty_id())){
                        selected_index=typelist.get(position).getSpecialty_id();
                        String price="";
                        for(int i=0;i<typelisthourly.size();i++)
                        {
                            if(typelisthourly.get(i).getSpecialty_id().equals(typelist.get(position).getSpecialty_id())){
                                price=typelisthourly.get(i).getPrice().toString();
                            }
                        }
                        priceti.setText(price);
                    }
                    else{
                        selected_index=typelist.get(position).getSpecialty_id();
                        priceti.setText("");
                    }






                }
            });


        }



        @Override
        public int getItemCount() {
            return typelist.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {

            public TextView type;

            public ViewHolder(View itemView) {
                super(itemView);

                this.type = (TextView) itemView.findViewById(R.id.name);

            }
        }
    }

    @Override
    protected void onStart() {
        getType();
        gethourly();
        super.onStart();
    }
    private void getType()
    {
        typelistt = new ArrayList<>();

        // Create a String request
        // using Volley Library
        String url = "https://myassistant.jaml46.net/APIs/assistant/select_specialty";

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
                            JSONArray jsonArray =new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject
                                        = jsonArray.getJSONObject(i);
                                typelistt.add(  new Type(jsonObject.getString("specialty_id"),jsonObject.getString("specialty_name")));
                            }

                            filllist(typelistt);

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

    private void EditHourly()
    {







        // Create a String request
        // using Volley Library
        String url = "https://myassistant.jaml46.net/APIs/assistant/add_hourly_work?assistant_id="+sharedpreferences.getString("assistantid","")+"&specialty_id="+selected_index+"&price="+priceti.getText();

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
                           JSONObject jsonObject =new JSONObject(response);
                           if(Integer.parseInt(jsonObject.getString("data"))==1){
                               new AlertDialog.Builder(HourlyServices.this)
                                       .setTitle("Success Edit")
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
                               new  AlertDialog.Builder(HourlyServices.this)
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
    private void gethourly()
    {
        typelisthourly = new ArrayList<>();
        typelisttid= new ArrayList<>();





        // Create a String request
        // using Volley Library

        String url = "https://myassistant.jaml46.net/APIs/assistant/select_hourly_work?assistant_id="+sharedpreferences.getString("assistantid","");

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
                            JSONArray jsonArray =new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject
                                        = jsonArray.getJSONObject(i);
                                System.out.println(jsonObject.getString("specialty_id"));
                                typelisttid.add(jsonObject.getString("specialty_id"));
                                typelisthourly.add(  new HoureRate(jsonObject.getString("specialty_id"),jsonObject.getString("price"),jsonObject.getString("specialty_name")));

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
