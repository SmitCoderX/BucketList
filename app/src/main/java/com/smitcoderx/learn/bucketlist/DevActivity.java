package com.smitcoderx.learn.bucketlist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DevActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);

        setTitle("About Developer");
    }

    public void openWeb(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://smitcoderx.github.io")));
    }

    public void openGit(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/SmitCoderX")));
    }

    public void openInsta(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/oyy_anirudh")));
    }

    public void openTwitter(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/anirudh16june")));
    }
}