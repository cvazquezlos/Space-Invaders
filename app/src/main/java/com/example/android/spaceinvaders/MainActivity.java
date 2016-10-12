package com.example.android.spaceinvaders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ImageButton opcionBoton;
    private PopupWindow popup;
    private RelativeLayout layoutPrincipal;

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

        opcionBoton = (ImageButton) findViewById(R.id.opcion_boton);
        layoutPrincipal = (RelativeLayout) findViewById(R.id.principal_screen);
        opcionBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View vistaPopup = inflater.inflate(R.layout.popup_activity, null);

                popup = new PopupWindow(
                        vistaPopup,
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );

                Button cerrarPop = (Button) vistaPopup.findViewById(R.id.volver_boton);
                cerrarPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popup.dismiss();
                    }
                });
                popup.showAtLocation(layoutPrincipal, Gravity.CENTER, 0, 0);

            }
        });
    }

}
