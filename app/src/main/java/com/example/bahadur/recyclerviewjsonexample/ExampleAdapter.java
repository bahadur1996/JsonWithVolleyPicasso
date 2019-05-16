package com.example.bahadur.recyclerviewjsonexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>{

    private Context mcontext;
    private ArrayList<ExampleItem>mExampleList;

    private OnItemClickListener mListener;

    public interface  OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }


    public ExampleAdapter(Context context,ArrayList<ExampleItem>exampleItems){
        mcontext=context;
        mExampleList=exampleItems;

    }


    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mcontext).inflate(R.layout.example_item,parent,false);
        return new ExampleViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem currentItem=mExampleList.get(position);
        String imageUrl=currentItem.getmImageUrl();
        String creatorName=currentItem.getmCreator();
        int likeCount=currentItem.getLikeCount();
        holder.mtextViewCreator.setText(creatorName);
        holder.mTextviewLikes.setText("Likes: "+likeCount);
        Picasso.with(mcontext).load(imageUrl).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder{


        private ImageView mImageView;
        private TextView mtextViewCreator;
        private TextView mTextviewLikes;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.image_view);
            mtextViewCreator=itemView.findViewById(R.id.textCreator);
            mTextviewLikes=itemView.findViewById(R.id.textLike);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }
}
