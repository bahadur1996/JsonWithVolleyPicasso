package com.example.bahadur.recyclerviewjsonexample;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AdapterView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener{

    public static final String EXTRA_URL="imageUrl";
    public static final String EXTRA_CREATOR="creatorName";
    public static final String EXTRA_LIKES="likecount";

    private RecyclerView mRecyclerView;
    private ExampleAdapter mexampleAdapter;
    private ArrayList<ExampleItem>mExampleList;
    private RequestQueue mRequestQueue;
private String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerId);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mExampleList=new ArrayList<>();
        mRequestQueue= Volley.newRequestQueue(this);


        parseJson();
    }
    private void parseJson(){
        String url="https://pixabay.com/api/?key=12498166-19dd4f29c244dbdc0db7eb472&q=yellow+flowers&image_type=photo";

        JsonObjectRequest request=new JsonObjectRequest(com.android.volley.Request.Method.GET,url,null,
        new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("hits");
                    for(int i=0;i<jsonArray.length();i++){

                        JSONObject hit=jsonArray.getJSONObject(i);
                        String CreatorName=hit.getString("user");
                        String imagerUrl=hit.getString("webformatURL");
                        int likeCount=hit.getInt("likes");
                        mExampleList.add(new ExampleItem(imagerUrl,CreatorName,likeCount));

                    }
                    mexampleAdapter=new ExampleAdapter(MainActivity.this,mExampleList);
                    Log.d(TAG,"Working properly");
                    mRecyclerView.setAdapter(mexampleAdapter);
                    mexampleAdapter.setOnItemClickListener(MainActivity.this);



                } catch (JSONException e) {
                    Log.d(TAG,"Working notproperly in catch");
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"Working notproperly");
               error.printStackTrace();
            }
        });
        mRequestQueue.add(request);

    }

    @Override
    public void onItemClick(int position) {

        Intent detailIntent=new Intent(this,DetailActivity.class);
        ExampleItem clickedItem=mExampleList.get(position);

        detailIntent.putExtra(EXTRA_URL,clickedItem.getmImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR,clickedItem.getmCreator());
        detailIntent.putExtra(EXTRA_LIKES,clickedItem.getLikeCount());

        startActivity(detailIntent);


    }
}
