package com.example.assignment.model;

import android.view.View;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import de.hdodenhof.circleimageview.CircleImageView;

public class Followers {

    @SerializedName("login")
    @Expose
    public String login;



    @SerializedName("avatar_url")
    @Expose
    public  String avatarUrl;

    public Followers() {
    }

    public Followers(String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    @BindingAdapter("android:followerImage")
    public static void loadImage(View view, String imageUrl) {
        CircleImageView circleImageView= (CircleImageView) view;
        Glide.with(view.getContext()).load(imageUrl).into(circleImageView);
    }
}
