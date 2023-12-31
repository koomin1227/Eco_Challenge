package com.ecochallenge.sns_project.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecochallenge.sns_project.R;
import com.ecochallenge.sns_project.UserInfo;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MainViewHolder> {
    private ArrayList<UserInfo> mDataset;
    private Activity activity;
    static class MainViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        MainViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public UserListAdapter(Activity activity, ArrayList<UserInfo> myDataset) {
        this.mDataset = myDataset;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    @NonNull
    @Override
    public UserListAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_list, parent, false);
        final MainViewHolder mainViewHolder = new MainViewHolder(cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MainViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        ImageView photoImageVIew = cardView.findViewById(R.id.photoImageVIew);
        TextView nameTextView = cardView.findViewById(R.id.nameTextView);
        TextView addressTextView = cardView.findViewById(R.id.addressTextView);
        TextView scoreTextView=cardView.findViewById(R.id.scoreTextView);
        ImageView myTierImageView = (ImageView) cardView.findViewById(R.id.myTierImageView);
        TextView myTier=(TextView) cardView.findViewById(R.id.rankingMyTierTextView);


        UserInfo userInfo = mDataset.get(position);
        if(mDataset.get(position).getPhotoUrl() != null){
            Glide.with(activity).load(mDataset.get(position).getPhotoUrl()).centerCrop().override(500).into(photoImageVIew);
        }
        nameTextView.setText(userInfo.getName());
        addressTextView.setText(String.valueOf(userInfo.getscore()));
        scoreTextView.setText(String.valueOf(userInfo.getRank()));
        makeTier(myTier, myTierImageView, userInfo.getscore());
        //rankColor(scoreTextView, userInfo.getRank());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void makeTier(TextView myTier, ImageView myTierImageView, long value) {
        String tier;
        int photo = R.drawable.ic_tier_seed;

        if (value >= 100) {
            photo = R.drawable.ic_tier_earth;
            tier = "지구";
        }
        else if (value >= 50) {
            photo = R.drawable.ic_tier_forest;
            tier = "숲";
        }
        else if (value >= 20) {
            photo = R.drawable.ic_tier_tree;
            tier = "나무";
        }
        else if (value >= 10) {
            photo = R.drawable.ic_tier_flower;
            tier = "꽃";
        }
        else if (value >= 1) {
            photo = R.drawable.ic_tier_leaf;
            tier = "떡잎";
        }
        else {
            photo = R.drawable.ic_tier_seed;
            tier = "씨앗";
        }

        myTierImageView.setImageResource(photo);
        myTier.setText(tier);
    }
}