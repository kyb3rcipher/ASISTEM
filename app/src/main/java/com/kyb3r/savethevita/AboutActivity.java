package com.kyb3r.savethevita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAbout);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Active contact hyperlink
        TextView developmentContact = (TextView) findViewById(R.id.developmentContact), designContact = (TextView) findViewById(R.id.designContact);
        developmentContact.setMovementMethod(LinkMovementMethod.getInstance());
        designContact.setMovementMethod(LinkMovementMethod.getInstance());
    }

}