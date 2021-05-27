package com.myassistant.manager;

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
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
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
import com.google.android.material.textfield.TextInputEditText;
import com.myassistant.manager.Model.feedbackitem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import android.view.Window;
public class  UserProfile extends AppCompatActivity {
    RecyclerView recyclerView;
    Button addrate;
    RatingBar ratingBar;
    String user_id;
    SwipeRefreshLayout swipeLayout;
    TextView birthdate,gender,phone,name;
    Intent intent;
    ArrayList<feedbackitem> feedback;
    Window window;
    ImageView profile;
    FeedbackRecycleAdapter feedbackRecycleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
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

        textView.setText("Profile");
        ImageView imageView= (ImageView)view.findViewById(R.id.back);
        intent=getIntent();
        user_id=intent.getStringExtra("user_id");
        addrate=findViewById(R.id.addrate);
        phone=findViewById(R.id.phone);
        birthdate=findViewById(R.id.birthdate);
        name=findViewById(R.id.tv_name);
        gender=findViewById(R.id.gender);

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
        addrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertadd = new AlertDialog.Builder(UserProfile.this);
                LayoutInflater factory = LayoutInflater.from(UserProfile.this);
                final View view = factory.inflate(R.layout.ratebardialog, null);
                Button send=view.findViewById(R.id.send);
                TextInputEditText title,messgae;
                RatingBar ratingBar;
                title=view.findViewById(R.id.title);
                ratingBar=view.findViewById(R.id.ratingBar);
                messgae=view.findViewById(R.id.messgae);
                alertadd.setView(view);
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ratingBar.getRating()==0){
                            new  AlertDialog.Builder(UserProfile.this)
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
                            new  AlertDialog.Builder(UserProfile.this)
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
                            new  AlertDialog.Builder(UserProfile.this)
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

                      int res=  addrate(messgae.getText().toString() ,title.getText().toString(),ratingBar.getRating());
                        if(res==1){
                            messgae.setText("");
                            ratingBar.setRating(0);
                            title.setText("");
                        }
                    }
                });
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
    public  int   addrate(String messgae,String Title,double rating){
        final int[] res = {0};
// Create a String request
        // using Volley Library
        System.out.println(ratingBar.getRating());
   double newrating=(ratingBar.getRating() +rating)/2;

        String url = "https://myassistant.jaml46.net/APIs/user/updaterate_addfeedback?user_id="+user_id+"&rating="+newrating+"&title="+Title+"&text="+messgae;

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
                                res[0] =1;
                                new AlertDialog.Builder(UserProfile.this)
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
                                new  AlertDialog.Builder(UserProfile.this)
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
 return  res[0];
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
            final String feedbackitem = feedback.get(position).getText();
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
        String url = "https://myassistant.jaml46.net/APIs/assistant/get_feedback_list?user_id="+user_id;

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
        getfeedback();
        getInfo();
        super.onStart();
    }
    private void getInfo()
    {

        // Create a String request
        // using Volley Library
        String url = "https://myassistant.jaml46.net/APIs/user/get_info?user_id="+user_id;

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