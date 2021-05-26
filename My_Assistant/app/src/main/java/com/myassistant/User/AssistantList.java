package com.myassistant.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.myassistant.User.Model.AssistantItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AssistantList extends AppCompatActivity {

    AssistantRecycleAdapter assistantRecycleAdapter;
    RecyclerView recyclerView;
    ArrayList<AssistantItem> assistantlist;
    ArrayList<AssistantItem> temp;
    ArrayList<CheckBox> checkbox;
    ArrayList<Integer> ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_list);
        //custome bar with center title

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.categoryfilter);
        View view =getSupportActionBar().getCustomView();
        TextView textView= (TextView)view.findViewById(R.id.title);
        textView.setText("Request Assistant");
        ImageView imageView= (ImageView)view.findViewById(R.id.back);
        ImageView filter= (ImageView)view.findViewById(R.id.filter);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


         recyclerView = findViewById(R.id.recyclerView);




        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AssistantList.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.customedialog, viewGroup, false);
                Button fillter =dialogView.findViewById(R.id.filtterbutton);
                LinearLayout linearLayout=(LinearLayout)dialogView.findViewById(R.id.linearlayout);

  for(int i =0;i<checkbox.size();i++){
      checkbox.get(i).setChecked(false);
      if(checkbox.get(i).getParent() != null){

      }
      else{
          linearLayout.addView(checkbox.get(i));
      }


  }

                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                fillter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ids=new ArrayList<>();
                        for(int i=0;i<checkbox.size();i++){
                            if(checkbox.get(i).isChecked()){
                                ids.add(checkbox.get(i).getId());
                            }
        }

                        filter();
                        alertDialog.dismiss();
                    }
                });
            }
        });

    }
    public void filter() {

        temp = new ArrayList<>();
        for (int i = 0; i < assistantlist.size(); i++) {
            if (ids.contains( Integer.parseInt(assistantlist.get(i).getSpecialty_id()) )) {
                temp.add(assistantlist.get(i));
            }
        }
        if(ids.size()==0){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            assistantRecycleAdapter = new AssistantRecycleAdapter( assistantlist,this);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(assistantRecycleAdapter);
        }
        else{
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            assistantRecycleAdapter = new AssistantRecycleAdapter( temp,this);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(assistantRecycleAdapter);
        }


    }

    public void filllist( ArrayList<AssistantItem> assistantlist){

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assistantRecycleAdapter = new AssistantRecycleAdapter( assistantlist,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(assistantRecycleAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

     class AssistantRecycleAdapter extends RecyclerView.Adapter<AssistantRecycleAdapter.ViewHolder>{
        List<AssistantItem> assistant;
        Context context;

        // RecyclerView recyclerView;
        public AssistantRecycleAdapter(List<AssistantItem> assistant,Context context) {
            this.assistant = assistant;
            this.context = context;
        }
        @Override
        public AssistantRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.assistantitem, parent, false);
           AssistantRecycleAdapter.ViewHolder viewHolder = new AssistantRecycleAdapter.ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AssistantRecycleAdapter.ViewHolder holder, int position) {
            final AssistantItem myListData = assistant.get(position);
             TextView textView;
             TextView type;
            textView = (TextView) holder.linearLayout.findViewById(R.id.name);
            type = (TextView) holder.linearLayout.findViewById(R.id.type);
            textView.setText(assistant.get(position).getFullname());
          type.setText(assistant.get(position).getSpecialty_name());
            Glide.with(context)
                    .load(assistant.get(position).getPhoto())
                    .circleCrop()
                    .into(   holder.imageView);

            holder.detaild.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(AssistantList.this, OrderDetails.class);
                    intent.putExtra("phone",assistant.get(position).getPhone());
                    intent.putExtra("assistant_id",assistant.get(position).getAssistant_id());
                    intent.putExtra("specialty_id",assistant.get(position).getSpecialty_id());
                    intent.putExtra("hourly_work_id",assistant.get(position).getHourly_work_id());
                    intent.putExtra("Specialty_name",assistant.get(position).getSpecialty_name());
                    startActivity(intent);
                }
            });
        }



        @Override
        public int getItemCount() {
            return assistant.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public  LinearLayout linearLayout;

            public Button detaild;
            public ViewHolder(View itemView) {
                super(itemView);
                this.imageView = (ImageView) itemView.findViewById(R.id.profile);
                this.linearLayout = (LinearLayout) itemView.findViewById(R.id.linear);
                this.detaild = (Button) itemView.findViewById(R.id.details);
            }
        }
    }
    private void getType()
    {


        checkbox=new ArrayList<>();

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
                                CheckBox checkBox =new CheckBox(getApplicationContext());
                                checkBox.setText(jsonObject.getString("specialty_name"));
                                checkBox.setId(Integer.parseInt(jsonObject.getString("specialty_id")));
                                checkbox.add(checkBox);
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
    private void getassistantlist()
    {


        assistantlist=new ArrayList<>();

        // Create a String request
        // using Volley Library
        String url = "https://myassistant.jaml46.net/APIs/user/req_assistant";

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
                              assistantlist.add(new AssistantItem(jsonObject.getString("assistant_id"),jsonObject.getString("fullname"),
                                      jsonObject.getString("phone"),jsonObject.getString("photo") ,
                                      jsonObject.getString("specialty_id"),jsonObject.getString("specialty_name") ,
                                      jsonObject.getString("price"),jsonObject.getString("hourly_work_id")));
                            }

                          filllist(assistantlist);

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
    protected void onStart()  {
        getType();
        getassistantlist();
        super.onStart();
    }
}

