package com.example.bahadur.recyclerviewjsonexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.bahadur.recyclerviewjsonexample.MainActivity.EXTRA_CREATOR;
import static com.example.bahadur.recyclerviewjsonexample.MainActivity.EXTRA_LIKES;
import static com.example.bahadur.recyclerviewjsonexample.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();
        String imageUrl=intent.getStringExtra(EXTRA_URL);
        String creatorName=intent.getStringExtra(EXTRA_CREATOR);
        int likeCount=intent.getIntExtra(EXTRA_LIKES,0);

        ImageView imageView= (ImageView) findViewById(R.id.DImageId);
        TextView textViewCreator= (TextView) findViewById(R.id.Dcreatorname);

        TextView textViewLikes= (TextView) findViewById(R.id.DLikes);
        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewCreator.setText(creatorName);
        textViewLikes.setText("Likes: "+likeCount);
    }
}
