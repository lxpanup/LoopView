package com.demo.loopview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<View> views = new ArrayList<>();
        final ArrayList<View> views2 = new ArrayList<>();
        final ArrayList<View> views3 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final TextView textView = new TextView(getApplicationContext());
            textView.setGravity(Gravity.CENTER);
            textView.setText(i + " - hello");
            textView.setBackgroundColor(i % 2 == 0 ? Color.RED : Color.GREEN);
            views.add(textView);
        }

        for (int i = 0; i < 10; i++) {
            final TextView textView = new TextView(getApplicationContext());
            textView.setGravity(Gravity.CENTER);
            textView.setText(i + " - hello");
            textView.setBackgroundColor(i % 2 == 0 ? Color.RED : Color.GREEN);
            views2.add(textView);
        }

        for (int i = 0; i < 10; i++) {
            final TextView textView = new TextView(getApplicationContext());
            textView.setGravity(Gravity.CENTER);
            textView.setText(i + " - hello");
            textView.setBackgroundColor(i % 2 == 0 ? Color.RED : Color.GREEN);
            views3.add(textView);
        }

        final LoopView loop = findViewById(R.id.loop1);
        loop.setLoopView(views);

        final LoopView loop2 = findViewById(R.id.loop2);
        loop2.setLoopView(views2);

        final LoopView loop3 = findViewById(R.id.loop3);
        loop3.setLoopView(views3);

        loop.setOnLoopItemClickListener(new LoopView.OnLoopItemClickListener() {
            @Override
            public void onLoopItemClick(int position, View view) {
                Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
