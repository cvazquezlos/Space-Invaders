package com.example.android.spaceinvaders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton playBoton = (ImageButton) findViewById(R.id.play_boton);
        playBoton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent gameScreen = new Intent(view.getContext(), GameActivity.class);
                startActivityForResult(gameScreen, 0);
                try {
                    this.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

}
