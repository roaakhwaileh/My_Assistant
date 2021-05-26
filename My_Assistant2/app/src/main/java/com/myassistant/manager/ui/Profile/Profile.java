package com.myassistant.manager.ui.Profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Base64;
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
import com.myassistant.manager.EditProfile;
import com.myassistant.manager.HomeActivity;
import com.myassistant.manager.Login;
import com.myassistant.manager.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class Profile extends Fragment {
    ImageView profile;
Button Delet;
    ImageView edit;
    TextView name,email,phone,birthdate,gender ;
    SharedPreferences sharedpreferences;
    String image;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.profile_fragment, container, false);
        profile=root.findViewById(R.id.imageview);
        edit=root.findViewById(R.id.edit);
        Delet=root.findViewById(R.id.delet);
        name=root.findViewById(R.id.tv_name);
        email=root.findViewById(R.id.email);
        phone=root.findViewById(R.id.phone);
        birthdate=root.findViewById(R.id.date);
        gender=root.findViewById(R.id.gender);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), EditProfile.class);
                intent.putExtra("name",name.getText());
                intent.putExtra("phone",phone.getText());
                intent.putExtra("email",email.getText());
                intent.putExtra("birthdate",birthdate.getText());
                intent.putExtra("gender",gender.getText());
                intent.putExtra("image",image);
                startActivity(intent);
            }
        });
        Delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              deleteaccount();
            }
        });

        return root;

    }

    private void deleteaccount()
    {

        // Create a String request
        // using Volley Library
        String url = "https://myassistant.jaml46.net/APIs/assistant/delete_assistant?assistant_id="+sharedpreferences.getString("assistantid","");;

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
                                    = new JSONObject(
                                    response.toString());

                            String data = jsonObject.get("data").toString();
                            System.out.println(jsonObject);
                            if(jsonObject.get("data").equals("1")){
                                Intent intent=new Intent(getContext(),Login.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                            }
                            else{
                                new  AlertDialog.Builder(getContext())
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
                                getContext(),
                                error.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        RequestQueue requestQueue
                = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
    private void getInfo()
    {

        // Create a String request
        // using Volley Library
        String url = "https://myassistant.jaml46.net/APIs/assistant/get_info?assistant_id="+sharedpreferences.getString("assistantid","");;

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

                            System.out.println(jsonObject);

                                name.setText(jsonObject.get("fullname").toString());
                                email.setText(jsonObject.get("email").toString());
                                phone.setText(jsonObject.get("phone").toString());
                                birthdate.setText(jsonObject.get("birthdate").toString());
                                gender.setText(jsonObject.get("gender").toString());
                            image=jsonObject.get("photo").toString();
                                Glide.with(getActivity())
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
                                getContext(),
                                error.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        RequestQueue requestQueue
                = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    @Override
    public void onStart() {
        sharedpreferences = getActivity().getSharedPreferences("Assistant_Id", Context.MODE_PRIVATE);

        getInfo();
        super.onStart();
    }
}