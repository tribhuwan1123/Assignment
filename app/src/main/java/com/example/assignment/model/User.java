package com.example.assignment.model;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import de.hdodenhof.circleimageview.CircleImageView;

public class User {
    @SerializedName("id")
    @Expose
    public String id;


    @SerializedName("login")
    @Expose
    public String login;



    @SerializedName("avatar_url")
    @Expose
    public  String avatarUrl;

    @SerializedName("html_url")
    @Expose
    public String htmlUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User() {
    }

    public User(String login, String avatarUrl, String htmlUrl,String id) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.htmlUrl = htmlUrl;
        this.id=id;
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

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @BindingAdapter("android:pImage")
    public static void loadImage(View view,String imageUrl) {
       CircleImageView circleImageView= (CircleImageView) view;
       Glide.with(view.getContext()).load(imageUrl).into(circleImageView);
    }
}
