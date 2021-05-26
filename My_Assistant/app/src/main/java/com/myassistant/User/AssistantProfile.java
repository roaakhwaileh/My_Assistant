package com.myassistant.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.bumptech.glide.Glide;
import com.myassistant.User.Model.feedbackitem;
import com.myassistant.User.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AssistantProfile extends AppCompatActivity {
    RecyclerView recyclerView;
    Intent intent;
    String assistant_id;
    Button personalidentity,disease,addrate;
    RatingBar ratingBar;
    SwipeRefreshLayout swipeLayout;
    TextView birthdate,gender,phone,name;
    ArrayList<feedbackitem> feedback;
    ImageView profile;
    String personalidentityurl,diseaseurl;
    FeedbackRecycleAdapter feedbackRecycleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_profile);
        //custome bar with center title

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_home);
        View view =getSupportActionBar().getCustomView();
        TextView textView= (TextView)view.findViewById(R.id.actionbar_textview);

        textView.setText("Profile");
        ImageView imageView= (ImageView)view.findViewById(R.id.back);

        phone=findViewById(R.id.phone);
        birthdate=findViewById(R.id.birthdate);
        name=findViewById(R.id.tv_name);
        gender=findViewById(R.id.gender);
        personalidentity=findViewById(R.id.personalidentity);
        addrate=findViewById(R.id.addrate);
        disease=findViewById(R.id.disease);
        intent=getIntent();

        swipeLayout = findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code here
                Toast.makeText(getApplicationContext(), "Refresh!", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        getfeedback();
                        swipeLayout.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });
        personalidentity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertadd = new AlertDialog.Builder(AssistantProfile.this);
                LayoutInflater factory = LayoutInflater.from(AssistantProfile.this);
                final View view = factory.inflate(R.layout.imagealertdialog, null);
                ImageView imageView1=view.findViewById(R.id.imageview);
                Glide.with(getApplicationContext())
                        .load(personalidentityurl)
                        .fitCenter()
                        .into(  imageView1);
                alertadd.setView(view);

                alertadd.show();
            }
        });
        addrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertadd = new AlertDialog.Builder(AssistantProfile.this);
                LayoutInflater factory = LayoutInflater.from(AssistantProfile.this);
                final View view = factory.inflate(R.layout.ratebardialog, null);
                Button send=view.findViewById(R.id.send);
                TextInputEditText title,messgae;
                RatingBar ratingBar;
                title=view.findViewById(R.id.title);
                ratingBar=view.findViewById(R.id.ratingBarr);
                messgae=view.findViewById(R.id.messgae);
                alertadd.setView(view);
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ratingBar.getRating()==0){
                            new  AlertDialog.Builder(AssistantProfile.this)
                                    .setTitle("Please Rate The User")
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
                        if(title.getText().toString().isEmpty()){
                            new  AlertDialog.Builder(AssistantProfile.this)
                                    .setTitle("Title Can't be Empty")
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
                        if(messgae.getText().toString().isEmpty()){
                            new  AlertDialog.Builder(AssistantProfile.this)
                                    .setTitle("Message Can't be Empty")
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

                         addrate(messgae ,title,ratingBar);

                    }
                });
                alertadd.show();
            }
        });
        disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertadd = new AlertDialog.Builder(AssistantProfile.this);
                LayoutInflater factory = LayoutInflater.from(AssistantProfile.this);
                final View view = factory.inflate(R.layout.imagealertdialog, null);
                ImageView imageView1=view.findViewById(R.id.imageview);
                Glide.with(getApplicationContext())
                        .load(diseaseurl)
                        .fitCenter()
                        .into(  imageView1);
                alertadd.setView(view);

                alertadd.show();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        profile=findViewById(R.id.profileimage);
        recyclerView = findViewById(R.id.recyclerView);
        ratingBar=findViewById(R.id.ratingBar);


    }
    public  void    addrate(TextView messgae,TextView Title,RatingBar rating){

// Create a String request
        // using Volley Library
        System.out.println(rating.getRating() + " "  +Title.getText() + " "+messgae.getText());
        double newrating=(rating.getRating() +rating.getRating())/2;

        String url = "https://myassistant.jaml46.net/APIs/assistant/updaterate_addfeedback?assistant_id="+assistant_id+"&rating="+newrating+"&title="+Title.getText()+"&text="+messgae.getText();

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

                                    messgae.setText("");
                                    ratingBar.setRating(0);
                                Title.setText("");

                                new AlertDialog.Builder(AssistantProfile.this)
                                        .setTitle("Success Rating ")
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
                                new  AlertDialog.Builder(AssistantProfile.this)
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
    public void filllist( ArrayList<feedbackitem> feedback){
        System.out.println(feedback.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        feedbackRecycleAdapter = new FeedbackRecycleAdapter( feedback,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(feedbackRecycleAdapter);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    class FeedbackRecycleAdapter extends RecyclerView.Adapter<FeedbackRecycleAdapter.ViewHolder>{
        List<feedbackitem> feedback;
        Context context;

        // RecyclerView recyclerView;
        public FeedbackRecycleAdapter(List<feedbackitem> feedback,Context context) {
            this.feedback = feedback;
            this.context = context;
        }
        @Override
        public FeedbackRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.feedbackitem, parent, false);
            FeedbackRecycleAdapter.ViewHolder viewHolder = new FeedbackRecycleAdapter.ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull FeedbackRecycleAdapter.ViewHolder holder, int position) {
            holder.textView.setText(feedback.get(position).getText());
            holder.title.setText(feedback.get(position).getTitle());
            holder.datetime.setText(feedback.get(position).getDatetime());



        }



        @Override
        public int getItemCount() {
            return feedback.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {

            public TextView title;
            public TextView textView;
            public TextView datetime;

            public ViewHolder(View itemView) {
                super(itemView);

                this.textView = (TextView) itemView.findViewById(R.id.text);
                this.title = (TextView) itemView.findViewById(R.id.title);
                this.datetime = (TextView) itemView.findViewById(R.id.datetime);

            }
        }
    }
    private void getfeedback()
    {
        feedback = new ArrayList<>();
        // Create a String request
        // using Volley Library
        String url = "https://myassistant.jaml46.net/APIs/user/get_feedback_list?assistant_id="+assistant_id;

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
                                feedback.add(new feedbackitem(jsonObject.getString("title"),jsonObject.getString("text"),jsonObject.getString("datetime")));
                            }

                            filllist(feedback);

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
    @Override
    protected void onStart() {
        assistant_id=intent.getStringExtra("assistant_id");

        getInfo();
        getfeedback();
        super.onStart();
    }
    private void getInfo()
    {


        // Create a String request
        // using Volley Library
        String url = "https://myassistant.jaml46.net/APIs/assistant/get_info?assistant_id="+assistant_id;

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

                            // Creating object of JSONObject
                            JSONObject jsonObject
                                    = jsonArray.getJSONObject(0);

                            System.out.println(jsonObject.get("fullname").toString());
                            name.setText(jsonObject.get("fullname").toString());
                            phone.setText(jsonObject.get("phone").toString());
                            birthdate.setText(jsonObject.get("birthdate").toString());
                            gender.setText(jsonObject.get("gender").toString());
                            ratingBar.setRating(Float.parseFloat(jsonObject.get("rating").toString()));
                            personalidentityurl=jsonObject.get("personal_identification").toString();
                            diseaseurl=jsonObject.get("disease_freedom").toString();
                            Glide.with(getApplicationContext())
                                    .load(jsonObject.get("photo").toString())
                                    .circleCrop()
                                    .into( profile);




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