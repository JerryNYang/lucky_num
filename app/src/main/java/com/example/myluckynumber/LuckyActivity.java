package com.example.myluckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class LuckyActivity extends AppCompatActivity {

    TextView welcomeTxt, luckyNoText;
    Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky);

        welcomeTxt = findViewById(R.id.textView2);
        luckyNoText = findViewById(R.id.lucky_number_txt);
        share_btn = findViewById(R.id.share_number_btn);

        // Username
        Intent i = getIntent();
        String userName = i.getStringExtra("name");
        Toast.makeText(this, "Username: " + userName, Toast.LENGTH_SHORT).show();

        // Random Number Generated
        int random_Num = generateRandom();
        luckyNoText.setText(""+ random_Num);

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                shareData(userName, random_Num);
            }
        });
    }

    public int generateRandom()
    {
        Random random = new Random();
        final int UPPER_LIMIT = 1000;

        int randomNumberGenerated = random.nextInt(UPPER_LIMIT);

        return randomNumberGenerated;

    }

    public void shareData(String username, int random_number)
    {

        // Implicit Intents
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        // convert the int to string
        String number = String.valueOf(random_number);

        i.putExtra(Intent.EXTRA_SUBJECT, username + " got lucky today");
        i.putExtra(Intent.EXTRA_TEXT, "His lucky number " + number);
        startActivity(Intent.createChooser(i, "Choose a platform"));
    }
}