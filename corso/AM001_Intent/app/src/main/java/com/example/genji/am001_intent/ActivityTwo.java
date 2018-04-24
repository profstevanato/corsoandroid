package com.example.genji.am001_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        /*Button apriSchermo3 = findViewById(R.id.apriSchermo3);
        apriSchermo3.setOnClickListener((View view) -> {
            Intent i = new Intent(this, Schermo3.class);
            startActivity(i);
        });*/

        /* Vecchio sistema prima di LAMBDA sintax */
        Button apriSchermo3 = (Button) findViewById(R.id.apriSchermo3);
        apriSchermo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityTwo.this, Schermo3.class);
                startActivity(i);
            }
        });
    }
}
