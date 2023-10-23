package com.kyb3r.asistem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAbout);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());


        // Start logo heart-pulse
        ImageView appLogo = findViewById(R.id.appLogo);
        Animation pulseAnimation = AnimationUtils.loadAnimation(this, R.anim.pulse_animation);
        appLogo.startAnimation(pulseAnimation);

        // Active contact hyperlink
        TextView developmentContact = (TextView) findViewById(R.id.developmentContact), designContact = (TextView) findViewById(R.id.designContact);
        developmentContact.setMovementMethod(LinkMovementMethod.getInstance());
        designContact.setMovementMethod(LinkMovementMethod.getInstance());
    }

}