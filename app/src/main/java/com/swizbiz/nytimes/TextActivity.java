package com.swizbiz.nytimes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TextActivity extends AppCompatActivity {
    private final static String MESSAGE = "message";

    public static void start(Activity activity, String message) {
        Intent intent = new Intent(activity, TextActivity.class);
        intent.putExtra(MESSAGE, message);
        activity.startActivity(intent);
    }

    private void composeEmail(String addresses, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No Email app found", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        TextView textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String message = extras.getString(MESSAGE, "");
                textView.setText(message);
            }
        }

        Button email = findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeEmail("andr.academy.msk@gmail.com", "Test subject text", "Test text in the body of letter");
            }
        });
    }
}
