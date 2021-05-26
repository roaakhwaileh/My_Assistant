  package com.myassistant.User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

  public class EditProfile extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ImageView imageView ;
      SharedPreferences sharedpreferences;
      String encode;
      TextInputEditText name,email,gender,phone;
      Button edit;
      TextView birthdate;
      Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        //custome bar with center title

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_home);
        View view =getSupportActionBar().getCustomView();
        TextView textView= (TextView)view.findViewById(R.id.actionbar_textview);

        textView.setText("Edit profile");
        ImageView back= (ImageView)view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        sharedpreferences = getSharedPreferences("User_Id", Context.MODE_PRIVATE);
        birthdate=findViewById(R.id.birthdate);
        name=findViewById(R.id.name);
        edit=findViewById(R.id.edit);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        gender=findViewById(R.id.gender);
        imageView=findViewById(R.id.imageview);
        final Calendar c = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                EditProfile.this, this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        intent=getIntent();
        birthdate.setText(intent.getStringExtra("birthdate"));
        email.setText(intent.getStringExtra("email"));
        phone.setText(intent.getStringExtra("phone"));
        gender.setText(intent.getStringExtra("gender"));
        name.setText(intent.getStringExtra("name"));
        System.out.println(intent.getStringExtra("image"));
        Glide.with(getApplicationContext())
                .load(intent.getStringExtra("image"))
                .circleCrop()
                .into( imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(EditProfile.this);
            }
        });
        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePickerDialog.show();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateinfo();
            }
        });
    }
      @Override
      protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
          super.onActivityResult(requestCode, resultCode, data);
          if(resultCode != RESULT_CANCELED) {
              switch (requestCode) {
                  case 0:
                      if (resultCode == RESULT_OK && data != null) {

                          try {
                              final Uri imageUri = data.getData();
                              final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                              final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                              encode=imagetostring(selectedImage);
                              updateimage(encode);

                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                      }
                      break;
                  case 1:
                      if (resultCode == RESULT_OK && data != null) {
                          Bitmap bitmap = null;
                          try {
                              bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                              encode=imagetostring(bitmap);
                              imageView.setImageBitmap(bitmap);
                              updateimage(encode);
                          } catch (IOException e) {
                              e.printStackTrace();
                          }


                      }
                      break;
              }
          }
      }
    private void selectImage(Context context) {
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
      private void updateimage(String image) {
          // url to post our data
          String url = "https://myassistant.jaml46.net/APIs/user/update_image";


          // creating a new variable for our request queue
          RequestQueue queue = Volley.newRequestQueue(EditProfile.this);

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
                          new AlertDialog.Builder(EditProfile.this)
                                  .setTitle("Success Change Image")
                                  .setMessage("")


                                  .setIcon(R.drawable.icon_success)
                                  .show();
                      }
                      else{
                          new  AlertDialog.Builder(EditProfile.this)
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
                  Toast.makeText(EditProfile.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
              }
          }) {
              @Override
              protected Map<String, String> getParams() {
                  // below line we are creating a map for
                  // storing our values in key and value pair.
                  Map<String, String> params = new HashMap<String, String>();

                  // on below line we are passing our key
                  // and value pair to our parameters.
                  params.put("user_id ", sharedpreferences.getString("userid",""));
                  params.put("photo", image);

                  // at last we are
                  // returning our params.
                  return params;
              }
          };
          // below line is to make
          // a json object request.
          queue.add(request);
      }
      private   String imagetostring( Bitmap bitmap){
          ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
          bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
          byte[] imagebyte=byteArrayOutputStream.toByteArray();
          String encodeimage=android.util.Base64.encodeToString(imagebyte, Base64.DEFAULT);
          return  encodeimage;
      }
      private void updateinfo()
      {

          // Create a String request
          // using Volley Library
          String url = "https://myassistant.jaml46.net/APIs/user/update_info?user_id="+sharedpreferences.getString("userid","")+"&fullname="+name.getText()+"&email="+email.getText()+"&phone="+phone.getText()+"&birthdate="+birthdate.getText()+"&gender="+gender.getText();

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
                                  new AlertDialog.Builder(EditProfile.this)
                                          .setTitle("Success Edit Profile")
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
                                  new  AlertDialog.Builder(EditProfile.this)
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

      @Override
      public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
          birthdate.setText(year + "/"+month+"/"+dayOfMonth);
      }
  }