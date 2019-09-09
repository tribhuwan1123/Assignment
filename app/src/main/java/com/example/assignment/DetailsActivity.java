package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    ImageView userImage;
    TextView uName,git;
    String imageUrl,username,gitLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        userImage=(ImageView)findViewById(R.id.userImage);
        uName=(TextView)findViewById(R.id.uName);
        git=(TextView)findViewById(R.id.git);

        Intent details= getIntent();
        imageUrl=details.getStringExtra("avatarUrl");
        username=details.getStringExtra("login");
        gitLink=details.getStringExtra("htmlUrl");
        Linkify.addLinks(git,Linkify.WEB_URLS);

        uName.setText(username);
        git.setText(gitLink);
        Glide.with(this).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(userImage);

    }
}
