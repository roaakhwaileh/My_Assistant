package com.myassistant.manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.bumptech.glide.Glide;
import com.myassistant.manager.Model.jobrequestitem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import android.view.Window;
public class JobRequestsList extends AppCompatActivity {
    JobRequestsRecycleAdapter jobRequestsRecycleAdapter;
    RecyclerView recyclerView;    SharedPreferences sharedpreferences;
    Window window;
    ArrayList<jobrequestitem> joblist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_requests_list);
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

        textView.setText("Job Requests");
        ImageView back= (ImageView)view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        sharedpreferences = getSharedPreferences("Assistant_Id", Context.MODE_PRIVATE);
        recyclerView = findViewById(R.id.recyclerView);


    }
    public void filllist( ArrayList<jobrequestitem> joblist){
        System.out.println(joblist.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jobRequestsRecycleAdapter = new JobRequestsRecycleAdapter( joblist,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(jobRequestsRecycleAdapter);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    class JobRequestsRecycleAdapter extends RecyclerView.Adapter<JobRequestsRecycleAdapter.ViewHolder>{
        List<jobrequestitem> jobrequest;
        Context context;

        // RecyclerView recyclerView;
        public JobRequestsRecycleAdapter(List<jobrequestitem> jobrequests,Context context) {
            this.jobrequest = jobrequests;
            this.context = context;
        }
        @Override
        public JobRequestsRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.jobrequestitem, parent, false);
            JobRequestsRecycleAdapter.ViewHolder viewHolder = new JobRequestsRecycleAdapter.ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull JobRequestsRecycleAdapter.ViewHolder holder, int position) {
            final jobrequestitem myListData = jobrequest.get(position);
            holder.type.setText(jobrequest.get(position).getSpecialty_name());
            holder.location.setText(jobrequest.get(position).getLocation());
            if(Integer.parseInt(jobrequest.get(position).getStatus())==0){
                holder.status.setText("waiting");
            }
            else if (Integer.parseInt(jobrequest.get(position).getStatus())==1){
                holder.status.setText("accept");
            }
            else if(Integer.parseInt(jobrequest.get(position).getStatus())==-1){
                holder.status.setText("reject");
            }

            holder.detaild.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(JobRequestsList.this, OrderDetails.class);
                    intent.putExtra("phone",jobrequest.get(position).getWhatsapp_phone());
                    intent.putExtra("req_assistant_id",jobrequest.get(position).getReq_assistant_id());
                    intent.putExtra("user_id",jobrequest.get(position).getUser_id());
                    intent.putExtra("descripption",jobrequest.get(position).getDescription());
                    intent.putExtra("location",jobrequest.get(position).getLocation());
                    startActivity(intent);
                }
            });
            holder.rejected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
             rejectorder(jobrequest.get(position));
                }
            });
        }



        @Override
        public int getItemCount() {
            return jobrequest.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {

            public TextView type;
            public Button detaild;
            public TextView location;
            public TextView status;
            public Button rejected;
            public ViewHolder(View itemView) {
                super(itemView);
                this.location = (TextView) itemView.findViewById(R.id.location);
                this.rejected = (Button) itemView.findViewById(R.id.reject);
                this.type = (TextView) itemView.findViewById(R.id.type);
                this.status = (TextView) itemView.findViewById(R.id.status);
                this.detaild = (Button) itemView.findViewById(R.id.details);
            }
        }
    }
    private void rejectorder(jobrequestitem jobrequestitem)
    {




        // Create a String request
        // using Volley Library

        String url = "https://myassistant.jaml46.net/APIs/assistant/reject_button?req_assistant_id="+jobrequestitem.getReq_assistant_id();

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
                                joblist.remove(jobrequestitem);
                                jobRequestsRecycleAdapter.notifyDataSetChanged();
                                new AlertDialog.Builder(JobRequestsList.this)
                                        .setTitle("Success Rejected ")
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
                                new  AlertDialog.Builder(JobRequestsList.this)
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
    private void getreqListlist()
    {
        joblist = new ArrayList<>();



        // Create a String request
        // using Volley Library

        String url = "https://myassistant.jaml46.net/APIs/assistant/list_req_assistant?assistant_id="+sharedpreferences.getString("assistantid","");

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
                                joblist.add(  new jobrequestitem(jsonObject.getString("req_assistant_id"),jsonObject.getString("user_id")
                                ,jsonObject.getString("description"),jsonObject.getString("whatsapp_phone")
                                ,jsonObject.getString("location"),jsonObject.getString("status")
                                ,jsonObject.getString("specialty_id"),jsonObject.getString("hourly_work_id")
                                ,jsonObject.getString("user_name"),jsonObject.getString("assistant_name")
                                ,jsonObject.getString("specialty_name")));

                            }

                            filllist(joblist);

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
        getreqListlist();
        super.onStart();
    }
}