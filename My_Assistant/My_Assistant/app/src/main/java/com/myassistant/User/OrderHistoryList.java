package com.myassistant.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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
import com.myassistant.User.Model.AssistantItem;
import com.myassistant.User.Model.orderhistoryitemmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import android.view.Window;
public class OrderHistoryList extends AppCompatActivity {
    ArrayList<orderhistoryitemmodel> orderHistoryList;
    OrderHistoryAdapter orderHistoryAdapter;
    RecyclerView recyclerView; SharedPreferences sharedpreferences;
    Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_list);
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
        textView.setText("Orders History");
        ImageView imageView= (ImageView)view.findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sharedpreferences = getSharedPreferences("User_Id", Context.MODE_PRIVATE);



    }
    public void filllist( ArrayList<orderhistoryitemmodel> orderHistoryList){
        System.out.println(orderHistoryList.size());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderHistoryAdapter = new OrderHistoryAdapter( orderHistoryList,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(orderHistoryAdapter);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder>{
        List<orderhistoryitemmodel> assistant;
        Context context;

        // RecyclerView recyclerView;
        public OrderHistoryAdapter(List<orderhistoryitemmodel> assistant,Context context) {
            this.assistant = assistant;
            this.context = context;
        }
        @Override
        public OrderHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.orderhistoryitem, parent, false);
            OrderHistoryAdapter.ViewHolder viewHolder =new  OrderHistoryAdapter.ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull OrderHistoryAdapter.ViewHolder holder, int position) {

            holder.type.setText(assistant.get(position).getSpecialty_name());
            if(assistant.get(position).getStatus().equals("0")){
                holder.status.setText("Waiting");
            }
            else if(assistant.get(position).getStatus().equals("1")){
                holder.status.setText("accept");
            }
            else if(assistant.get(position).getStatus().equals("-1")){
                holder.status.setText("reject");
            }
            holder.detaild.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(OrderHistoryList.this, orderHistoryDetails.class);
                    intent.putExtra("phone",assistant.get(position).getWhatsapp_phone());
                    intent.putExtra("location",assistant.get(position).getLocation());
                    intent.putExtra("description",assistant.get(position).getDescription());
                    intent.putExtra("jobtype",assistant.get(position).getSpecialty_name());
                    intent.putExtra("assistant_id",assistant.get(position).getAssistant_id());

                    startActivity(intent);
                }
            });
        }



        @Override
        public int getItemCount() {
            return assistant.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {
            public TextView type;
            public TextView status;
            public Button detaild;
            public ViewHolder(View itemView) {
                super(itemView);
                this.type = (TextView) itemView.findViewById(R.id.type);
                this.status = (TextView) itemView.findViewById(R.id.status);
                this.detaild = (Button) itemView.findViewById(R.id.details);
            }
        }
    }
    private void getorderhistorylist()
    {

        recyclerView = findViewById(R.id.recyclerView);
        orderHistoryList=new ArrayList<>();

        // Create a String request
        // using Volley Library
        String url = "https://myassistant.jaml46.net/APIs/user/order_history?user_id="+sharedpreferences.getString("userid","");

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
                                orderHistoryList.add(new orderhistoryitemmodel(jsonObject.getString("req_assistant_id"),jsonObject.getString("assistant_id"),
                                        jsonObject.getString("description"),jsonObject.getString("whatsapp_phone") ,
                                        jsonObject.getString("location"),jsonObject.getString("status") ,
                                        jsonObject.getString("specialty_id"),jsonObject.getString("hourly_work_id"),
                                        jsonObject.getString("user_name"),
                                        jsonObject.getString("assistant_name"),jsonObject.getString("specialty_name")
                                      ));
                            }

                            filllist(orderHistoryList);

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
        getorderhistorylist();
        super.onStart();
    }
}