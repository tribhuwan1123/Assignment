package com.example.assignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.DetailsActivity;
import com.example.assignment.R;
import com.example.assignment.databinding.FollowersListBinding;
import com.example.assignment.databinding.FollowingListBinding;
import com.example.assignment.model.Followers;
import com.example.assignment.model.Following;

import java.util.List;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ViewHolder> {

    private List<Following> followingList;
    Context context;

    public FollowingAdapter(Context context, List<Following> followingList) {
        this.followingList = followingList;
        this.context = context;
    }

    public FollowingAdapter() {

    }

    @NonNull
    @Override
    public FollowingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FollowingListBinding followingListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.following_list, parent, false);
        return new ViewHolder(followingListBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull FollowingAdapter.ViewHolder holder, int position) {
        Following following = followingList.get(position);
        holder.followingListBinding.setFollowing(following);


    }

    @Override
    public int getItemCount() {
        return followingList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        FollowingListBinding followingListBinding;

        public ViewHolder(@NonNull FollowingListBinding itemView) {
            super(itemView.getRoot());
            followingListBinding = itemView;
        }


    }
}
