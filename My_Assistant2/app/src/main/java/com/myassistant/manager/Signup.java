package com.myassistant.manager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.google.android.material.textfield.TextInputEditText;
import com.myassistant.manager.Model.Type;
import com.myassistant.manager.Model.feedbackitem;
import com.myassistant.manager.Model.jobrequestitem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.view.Window;
public class Signup extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TypeAdapter typeAdapter;
    RecyclerView recyclerView;
    ArrayList<Type> typelist;
    ArrayList<Type>selectedindex;
    String type="avatar";
    String idencode;
    String profileencode;
    SharedPreferences sharedpreferences;
    String disesencode;
    TextView date;
    Window window;
    TextInputEditText name,pass,email,gender,phone;

    private   String imagetostring( Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imagebyte=byteArrayOutputStream.toByteArray();
        String encodeimage=android.util.Base64.encodeToString(imagebyte, Base64.DEFAULT);
        return  encodeimage;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null ) {
                        try {
                            if(type=="avatar"){
                                final Uri imageUri = data.getData();
                                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                                profileencode=imagetostring(selectedImage);
                                imageView.setImageBitmap(selectedImage);
                            }
                            else if (type=="idebtify"){
                                final Uri imageUri = data.getData();
                                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                                idencode=imagetostring(selectedImage);
                                imageView.setImageBitmap(selectedImage);
                            }
                            else if (type=="disease"){
                                final Uri imageUri = data.getData();
                                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                                disesencode=imagetostring(selectedImage);
                                imageView.setImageBitmap(selectedImage);
                            }


                        }
                        catch (Exception e){

                        }


                    }
//

                    break;
                case 1:
                    Bitmap bitmap = null;
                    if (resultCode == RESULT_OK && data != null) {
                        try {
                            if(type=="avatar"){
                                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                                profileencode=imagetostring(bitmap);
                                imageView.setImageBitmap(bitmap);

                            }
                            else if(type=="disease"){

                                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                                disesencode=imagetostring(bitmap);
                                imageView.setImageBitmap(bitmap);


                            }
                            else if(type=="idebtify"){

                                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                                idencode=imagetostring(bitmap);
                                imageView.setImageBitmap(bitmap);


                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }

    Button login,idebtify,disease,signup;
    ImageView imageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //custome bar with center title
        if(Build.VERSION.SDK_INT>=21){
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = new TextView(this);
        textView.setText("Sign uP");
        textView.setTextSize(20);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
        login=findViewById(R.id.Button);
        signup=findViewById(R.id.reg_register);
        gender=findViewById(R.id.gender);
        phone=findViewById(R.id.phone);
        date=findViewById(R.id.birthdate);
        final Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Signup.this, this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePickerDialog.show();
            }
        });
        name=findViewById(R.id.reg_username);
        pass=findViewById(R.id.reg_password);
        email=findViewById(R.id.reg_email);
        imageView=findViewById(R.id.my_avatar);
        idebtify=findViewById(R.id.idebtify);
        disease=findViewById(R.id.disease);
        imageView.setImageResource(R.drawable.person);
        sharedpreferences = getSharedPreferences("Assistant_Id", Context.MODE_PRIVATE);
        idebtify.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectidebtifyImage(Signup.this);
                    }
                }
        );
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty()){
                    new  AlertDialog.Builder(Signup.this)
                            .setTitle("Name Required")
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
                if(email.getText().toString().isEmpty()){
                    new  AlertDialog.Builder(Signup.this)
                            .setTitle("Email Required")
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
                if(pass.getText().toString().isEmpty()){
                    new  AlertDialog.Builder(Signup.this)
                            .setTitle("Password Required")
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
                if(profileencode==null){
                    new  AlertDialog.Builder(Signup.this)
                            .setTitle("Profile Image Required")
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
                if(idencode==null){
                    new  AlertDialog.Builder(Signup.this)
                            .setTitle("Personal  Identity Required")
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
                if(disesencode==null){
                    new  AlertDialog.Builder(Signup.this)
                            .setTitle("Disease Certificate Required")
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
                if(selectedindex.size()==0){
                    new  AlertDialog.Builder(Signup.this)
                            .setTitle("Type Required")
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
                else{
                    String index="";
                    for(int i=0;i<selectedindex.size();i++){
                        if(i==selectedindex.size()-1){
                            index+=selectedindex.get(i).getSpecialty_id();
                        }
                        else{
                            index+=selectedindex.get(i).getSpecialty_id()+",";
                        }

                    }
                    Signup(name,email,pass,index,gender,phone);
                }

            }
        });
        disease.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectdiseaseImage(Signup.this);
                    }
                }
        );
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(Signup.this);
            }
        });
        recyclerView = findViewById(R.id.recyclerView);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signup.this,Login.class);
                startActivity(intent);
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
    private void selectidebtifyImage(Context context) {
        type="idebtify";
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }



    private void selectdiseaseImage(Context context) {
        type="disease";
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }
    private void selectImage(Context context) {
        type="avatar";
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }
    private void Signup(TextInputEditText name,TextInputEditText email,TextInputEditText pass,String index,TextInputEditText gender,TextInputEditText phone) {

        // url to post our data
        String url = "https://myassistant.jaml46.net/APIs/assistant/signup";


        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(Signup.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                // on below line we are displaying a success toast message.

                try {


                    JSONObject respObj = new JSONObject(response);
                    if(respObj.getString("data").toString()!="0"){
                        name.setText("");
                        email.setText("");
                        pass.setText("");
                        phone.setText("");
                        gender.setText("");
                        disesencode=null;
                        profileencode=null;
                        idencode=null;
                        date.setText("");

                        new AlertDialog.Builder(Signup.this)
                                .setTitle("Success Signup ")
                                .setMessage("")


                                .setIcon(R.drawable.icon_success)
                                .show();
                    }
                    else{
                        new  AlertDialog.Builder(Signup.this)
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



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(Signup.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key
                // and value pair to our parameters.

                params.put("fullname", name.getText().toString());
                params.put("password", pass.getText().toString());
                params.put("personal_identification", idencode);
                params.put("disease_freedom", disesencode);
                params.put("photo", profileencode);
                params.put("phone", phone.getText().toString());
                params.put("email", email.getText().toString());
                params.put("gender", gender.getText().toString());
                params.put("birthdate", date.getText().toString());
                params.put("details_specialty", index);

                // at last we are
                // returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date.setText(year + "/"+month+"/"+dayOfMonth);
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
            holder.itemView.setBackgroundColor(Color.GRAY);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(typelist.get(position).getSpecialty_id());
                    if(selectedindex.contains(typelist.get(position))){
                        selectedindex.remove(typelist.get(position));
                        holder.itemView.setBackgroundColor(Color.GRAY);
                    }
                    else{
                        selectedindex.add(typelist.get(position));
                        holder.itemView.setBackgroundColor(Color.GREEN);
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
    private void getType()
    {       selectedindex=new ArrayList<>();
        typelist = new ArrayList<>();



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
                                typelist.add(  new Type(jsonObject.getString("specialty_id"),jsonObject.getString("specialty_name")));
                            }

                            filllist(typelist);

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
        getType();
        super.onStart();
    }
}