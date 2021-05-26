package com.myassistant.manager.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.myassistant.manager.AboutUs;
import com.myassistant.manager.HourlyServices;
import com.myassistant.manager.JobRequestsList;
import com.myassistant.manager.Model.SliderData;
import com.myassistant.manager.R;
import com.myassistant.manager.adapter.SliderAdapter;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    TextView AboutUs,Rate,OrderHistory,jobrequests;
    private HomeViewModel homeViewModel;
    ArrayList<SliderData> sliderDataArrayList;
    SliderView sliderView;     SliderAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        AboutUs=root.findViewById(R.id.aboutus);
        Rate=root.findViewById(R.id.rate);
        sliderView = root.findViewById(R.id.slider);
        jobrequests=root.findViewById(R.id.jobrequests);
        OrderHistory=root.findViewById(R.id.OrderHistory);
        fetchdata();
        Rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HourlyServices.class);
                startActivity(intent);
            }
        });
        OrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), com.myassistant.manager.OrderHistory.class);
                startActivity(intent);
            }
        });
        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), com.myassistant.manager.AboutUs.class);
                startActivity(intent);
            }
        });

        jobrequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), JobRequestsList.class);
                startActivity(intent);
            }
        });
        // we are creating array list for storing our image urls.



        return root;


    }
    private void fetchdata()
    {
        sliderDataArrayList = new ArrayList<>();
        // Create a String request
        // using Volley Library
        String url = "https://myassistant.jaml46.net/APIs/assistant/get_slider";

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

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject row = array.getJSONObject(i);
                                sliderDataArrayList  .add(new SliderData(row.getString("image_url")));

                            }
                            // initializing the slider view.



                            // passing this array list inside our adapter class.
                             adapter = new SliderAdapter(getActivity(), sliderDataArrayList);

                            // below method is used to set auto cycle direction in left to
                            // right direction you can change according to requirement.
                            sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

                            // below method is used to
                            // setadapter to sliderview.
                            sliderView.setSliderAdapter(adapter);

                            // below method is use to set
                            // scroll time in seconds.
                            sliderView.setScrollTimeInSec(3);

                            // to set it scrollable automatically
                            // we use below method.
                            sliderView.setAutoCycle(true);

                            // to start autocycle below method is used.
                            sliderView.startAutoCycle();

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
}